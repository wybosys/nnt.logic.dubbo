package com.nnt.core

import com.eclipsesource.v8.*

private class V8T {
    companion object {

        fun array(arr: V8Array): List<Any?> {
            val r = mutableListOf<Any?>()
            for (i in 0..arr.length()) {
                val e = arr[i]
            }
            return r
        }

        fun array(v8: V8, vararg p: Any?): V8Array {
            val r = V8Array(v8)
            p.forEach {
                if (it == null) {
                    r.pushNull()
                } else if (it is Array<*>) {
                    r.push(array(v8, *it))
                } else if (it is Map<*, *>) {
                    r.push(map(v8, it))
                } else if (it is JsCallback) {
                    r.push(callback(v8) { _, params ->
                        val ps = array(params)
                        it.invoke(ps[0] as Error, ps.subList(1, ps.size))
                    })
                } else {
                    r.push(it)
                }
            }
            return r
        }

        fun map(v8: V8, ps: Map<*, *>): V8Object {
            val r = V8Object(v8)
            ps.forEach { (k, v) ->
                val ks = k.toString()
                if (v == null) {
                    r.addNull(ks)
                } else if (v is Number) {
                    r.add(ks, v.toInt())
                } else if (v is String) {
                    r.add(ks, v)
                } else if (v is Boolean) {
                    r.add(ks, v)
                } else if (v is Array<*>) {
                    r.add(ks, array(v8, *v))
                } else if (v is Map<*, *>) {
                    r.add(ks, map(v8, v))
                } else if (v is JsCallback) {
                    r.add(ks, callback(v8) { _, params ->
                        val ps = array(params)
                        v.invoke(ps[0] as Error, ps.subList(1, ps.size))
                    })
                }
            }
            return r
        }

        fun callback(v8: V8, cb: (receiver: V8Object, parameters: V8Array) -> Unit): V8Function {
            return V8Function(v8, object : JavaCallback {
                override fun invoke(receiver: V8Object, parameters: V8Array): Any {
                    cb(receiver, parameters)
                    return V8Object(v8)
                }
            })
        }

    }
}

interface JsCallback {
    fun invoke(err: Error?, params: List<Any?>)
}

class JsObject(obj: V8Object?, v8: V8) {

    private val _obj = obj
    private val _v8 = v8

    val isNull: Boolean get() = _obj == null

    fun invoke(func: String, vararg args: Any?): Any? {
        _v8.locker.acquire()
        val r = _obj!!.executeFunction(func, V8T.array(_v8, *args))
        _v8.locker.release()
        return r
    }
}

class JsEngine {

    private val _v8 = V8.createV8Runtime()

    init {
        _v8.locker.release()
    }

    protected fun finalize() {
        _v8.release()
    }

    fun get(key: String): JsObject {
        _v8.locker.acquire()
        val r = JsObject(_v8.getObject(key), _v8)
        _v8.locker.release()
        return r
    }

    fun eval(script: String) {
        _v8.locker.acquire()
        _v8.executeScript(script)
        _v8.locker.release()
    }
}