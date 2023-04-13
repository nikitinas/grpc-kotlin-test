/*
 * Copyright 2020 gRPC authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nikitinas.grpc.hello

import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HelloServer(private val port: Int) {

    private val server: Server = ServerBuilder
        .forPort(port)
        .addService(HelloService())
        .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                this@HelloServer.stop()
                println("*** server shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    internal class HelloService : GreeterGrpcKt.GreeterCoroutineImplBase() {
        override suspend fun helloCall(request: HelloRequest): Reply {
            println("Received hello request from ${request.name}")
            return reply {
                message = "Hello, " + request.name + "!"
            }
        }

        override fun spell(request: SpellRequest): Flow<Reply> = flow {
            println("Received spell request for '${request.text}'")
            request.text.forEach { c ->
                val reply = reply {
                    message = c.uppercase()
                }
                emit(reply)
                delay(1000)
            }
        }
    }
}

fun main() {
    val port = 50051
    val server = HelloServer(port)
    server.start()
    server.blockUntilShutdown()
}
