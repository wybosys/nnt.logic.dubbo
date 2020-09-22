package com.nnt.logic.thirds.dubbo

import com.alibaba.dubbo.config.ServiceConfig

class ServiceConfig : ServiceConfig<Any>() {

    lateinit var serviceClass: Class<*>

    override fun getServiceClass(ref: Any?): Class<*> {
        return serviceClass
    }
}