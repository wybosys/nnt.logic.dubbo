package com.nnt.core

import com.nnt.manager.Config
import com.nnt.manager.IsLocal
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions

interface IRouter {

    // router 的标记
    val action: String

    // 接受配置文件的设置
    fun config(node: JsonObject): Boolean
}

// action可用的模式
const val debug = "debug"
const val develop = "develop"
const val local = "local"
const val expose = "expose"
const val devops = "devops"
const val devopsdevelop = "devopsdevelop"
const val devopsrelease = "devopsrelease"

class ActionProto {

    // 绑定的模型类型
    lateinit var clazz: KClass<*>

    // 限制debug可用
    var debug: Boolean = false

    // 限制develop可用
    var develop: Boolean = false

    // 限制local可用
    var local: Boolean = false

    // 限制devops可用
    var devops: Boolean = false

    // 限制devopsdevelop可用
    var devopsdevelop: Boolean = false

    // 限制devopsrelease可用
    var devopsrelease: Boolean = false

    // 注释
    var comment: String = ""

    // 暴露接口
    var expose: Boolean = false

    // 对应的函数对象
    lateinit var func: KFunction<*>
}

annotation class action(val modelType: KClass<*>, val options: Array<String> = [], val comment: String = "")

typealias ActionProtoStore = MutableMap<KClass<*>, MutableMap<String, ActionProto>>

private val actions: ActionProtoStore = mutableMapOf()

// 查找action定义
fun FindAction(target: Any, key: String): ActionProto? {
    // 不需要加锁，框架启动时会根据配置初始化所有的action信息，只有test代码才存在动态添加的需求
    val clz = target.javaClass.kotlin
    var aps = actions[clz]
    if (aps != null) {
        val ap = aps[key]
        if (ap != null)
            return ap
    }

    // 测试时才会动态去查找并加入actions
    if (!IsLocal()) {
        return null
    }

    if (aps == null) {
        aps = mutableMapOf()
        actions[clz] = aps
    }

    // 读取
    clz.declaredMemberFunctions.forEach { fn ->
        fn.annotations.forEach { ann ->
            if (!(ann is action))
                return@forEach

            val ap = ActionProto()
            ap.clazz = ann.modelType
            ap.comment = ann.comment
            ap.func = fn

            ann.options.forEach {
                when (it) {
                    debug -> ap.debug = true
                    develop -> ap.develop = true
                    local -> ap.local = true
                    devops -> ap.devops = true
                    devopsdevelop -> ap.devopsdevelop = true
                    devopsrelease -> ap.devopsrelease = true
                    expose -> ap.expose = true
                }
            }

            var pass = true

            if (ap.develop || ap.develop || ap.local || ap.devops || ap.devopsdevelop || ap.devopsrelease) {
                pass = false
                // 挨个判断是否可以定义
                if (!pass && ap.debug) {
                    if (Config.DEBUG)
                        pass = true
                }
                if (!pass && ap.develop) {
                    if (Config.DEVELOP)
                        pass = true
                }
                if (!pass && ap.local) {
                    if (Config.LOCAL)
                        pass = true
                }
                if (!pass && ap.devops) {
                    if (Config.DEVOPS)
                        pass = true
                }
                if (!pass && ap.devopsdevelop) {
                    if (Config.DEVOPS_DEVELOP)
                        pass = true
                }
                if (!pass && ap.devopsrelease) {
                    if (Config.DEVOPS_RELEASE)
                        pass = true
                }
            }

            // 测试通过，该action生效
            if (pass)
                aps[fn.name] = ap
        }
    }

    return aps[key]
}

fun GetAllActionNames(obj: Any): Set<String> {
    val clz = obj.javaClass.kotlin
    val aps = actions[clz]
    if (aps != null) {
        return aps.keys
    }
    return setOf()
}