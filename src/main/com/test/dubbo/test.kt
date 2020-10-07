package com.test.dubbo

import com.google.protobuf.BoolValue
import com.google.protobuf.Empty
import com.google.protobuf.Int32Value
import com.google.protobuf.StringValue
import com.nnt.core.logger
import com.nnt.core.toJson
import com.nnt.manager.Dbms
import com.nnt.store.RMysql
import com.test.Dao
import com.test.Echoo
import com.test.Sample
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
interface ITest : TestGrpc.ITest {

    @GET
    @Path("hello")
    fun hello(): String
}

class Test : ITest, TestGrpc.TestImplBase() {

    override fun hello(): String {
        logger.info("调用 rest-hello")
        return toJson(mapOf("msg" to "HELLO"))
    }

    // grpc协议下必须实现该函数
    fun setProxiedImpl(impl: ITest) {
        super.setProxiedImpl(impl)
    }

    override fun hello(request: Empty, responseObserver: StreamObserver<TestOuterClass.TestReply>) {
        logger.info("调用 grpc-hello")

        val reply = TestOuterClass.TestReply.newBuilder().setMessage("hello grpc").build()
        responseObserver.onNext(reply)
        responseObserver.onCompleted()
    }

    override fun echoo(request: StringValue, responseObserver: StreamObserver<Dao.Echoo>) {
        logger.info("调用 grpc-echoo")

        GlobalScope.launch {
            val mysql = Dbms.Find("mysql") as RMysql
            mysql.execute {
                val m = Echoo()
                m.input = request.value
                m.output = request.value

                val map = it.getMapper(Sample::class.java)
                map.echoo(m)

                val reply = Dao.Echoo.newBuilder()
                    .setId(m.id)
                    .setInput(m.input)
                    .setOutput(m.output)
                    .build()
                responseObserver.onNext(reply)
                responseObserver.onCompleted()
            }
        }
    }

    override fun updateEchoo(request: Dao.Echoo, responseObserver: StreamObserver<BoolValue>) {
        logger.info("调用 grpc-update-echoo")
        GlobalScope.launch {
            val mysql = Dbms.Find("mysql") as RMysql
            mysql.execute {
                val map = it.getMapper(Sample::class.java)
                val m = Echoo()
                m.id = request.id
                m.input = request.input
                m.output = request.output
                val rows = map.updateEchoo(m)

                responseObserver.onNext(BoolValue.of(rows == 1))
                responseObserver.onCompleted()
            }
        }
    }

    override fun clearEchoo(request: Empty, responseObserver: StreamObserver<Int32Value>) {
        logger.info("调用 grpc-clear-echoo")
        GlobalScope.launch {
            val mysql = Dbms.Find("mysql") as RMysql
            mysql.execute {
                val map = it.getMapper(Sample::class.java)
                val rows = map.clearEchoo()

                responseObserver.onNext(Int32Value.of(rows))
                responseObserver.onCompleted()
            }
        }
    }

}