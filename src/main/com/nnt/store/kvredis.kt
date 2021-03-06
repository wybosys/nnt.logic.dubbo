package com.nnt.store

import com.nnt.core.JsonObject
import com.nnt.core.logger
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

private const val DEFAULT_PORT = 6379

open class KvRedis : AbstractKv() {

    var host: String = ""
    var port: Int = DEFAULT_PORT
    var prefix: String? = null

    override fun config(cfg: JsonObject): Boolean {
        if (!super.config(cfg))
            return false

        if (!cfg.has("host")) {
            logger.fatal("${id} 没有配置数据库地址")
            return false
        }
        val th = cfg["host"]!!.asString()
        if (th.startsWith("unix://")) {
            logger.fatal("${id} java不支持使用管道连接redis")
            return false
        } else {
            val sp = th.split(":")
            if (sp.size == 1) {
                host = th
            } else {
                host = sp[0]
                port = sp[1].toInt()
            }
        }

        if (cfg.has("prefix"))
            prefix = cfg["prefix"]!!.asString()
        return true
    }

    private lateinit var _pool: JedisPool

    override fun open() {
        val cfg = JedisPoolConfig()
        cfg.maxTotal = Runtime.getRuntime().availableProcessors() * 2
        _pool = JedisPool(cfg, host, port)

        if (execute {
                it.ping()
            }) {
            logger.info("连接 ${id}@redis")
        } else {
            logger.info("连接 ${id}@redis 失败")
        }
    }

    fun execute(proc: (redis: RedisClient) -> Unit): Boolean {
        var r = true
        try {
            val t = RedisClient(_pool.resource, this)
            try {
                proc(t)
            } catch (err: Throwable) {
                r = false
                logger.exception(err)
            }
            t.close()
        } catch (err: Throwable) {
            r = false
            logger.exception(err)
        }
        return r
    }

    override fun close() {
        _pool.close()
    }

    override fun acquireSession(): ISession {
        return acquire()
    }

    open fun acquire(): RedisSession {
        val cli = RedisClient(_pool.resource, this)
        return RedisSession(cli)
    }
}

// redis业务对象
open class RedisSession(redis: RedisClient) : ISession, RedisClientOperations by redis {

    private val _redis = redis
    private var _closed = false

    override fun close() {
        if (!_closed) {
            _redis.close()
            _closed = true
        }
    }

    override fun commit() {
        // pass
    }

    override fun rollback() {
        // pass
    }

    protected fun finalize() {
        close()
    }

}