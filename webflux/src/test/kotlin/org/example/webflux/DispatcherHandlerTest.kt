package org.example.webflux

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldNotBeEmpty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

import org.springframework.web.reactive.DispatcherHandler
import org.springframework.web.reactive.function.server.support.RouterFunctionMapping
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping

@SpringBootTest
class DispatcherHandlerTest : FunSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var applicationContext: ApplicationContext

    private lateinit var dispatcherHandler: DispatcherHandler

    init {
        beforeTest {
            dispatcherHandler = DispatcherHandler()
        }

        context("Dispatcher Handler 초기화 테스트") {
            test("모든 전략들이 적절히 초기화되어야 한다") {
                dispatcherHandler.setApplicationContext(applicationContext)

                val handlersField = DispatcherHandler::class.java
                    .getDeclaredField("handlerMappings")
                    .apply { isAccessible = true }

                val handlerMappings = handlersField.get(dispatcherHandler) as List<*>
                handlerMappings.filterIsInstance<RouterFunctionMapping>().shouldNotBeEmpty()
                handlerMappings.filterIsInstance<RequestMappingHandlerMapping>().shouldNotBeEmpty()
            }
        }
    }
}