package org.gosky.aroundight.verticle

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.CorsHandler
import mu.KotlinLogging
import org.gosky.aroundight.ext.error
import java.util.*

/**
 * @Auther: guozhong
 * @Date: 2019-02-24 14:50
 * @Description:
 */

private val logger = KotlinLogging.logger {}

abstract class RestVerticle : AbstractVerticle() {
    protected lateinit var router: Router


    @Throws(Exception::class)
    override fun start() {
        super.start()
        router = Router.router(vertx)

        // CORS support
        val allowedHeaders = HashSet<String>()
        allowedHeaders.add("x-requested-with")
        //        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("Access-Control-Allow-Headers")
        allowedHeaders.add("origin")
        allowedHeaders.add("Content-Type")
        allowedHeaders.add("accept")
        allowedHeaders.add("authorization")
        allowedHeaders.add("X-PINGARUNER")

        val allowedMethods = HashSet<HttpMethod>()
        allowedMethods.add(HttpMethod.GET)
        allowedMethods.add(HttpMethod.POST)
        allowedMethods.add(HttpMethod.OPTIONS)
        /*
         * these methods aren't necessary for this sample,
         * but you may need them for your projects
         */
        allowedMethods.add(HttpMethod.DELETE)
        allowedMethods.add(HttpMethod.PATCH)
        allowedMethods.add(HttpMethod.PUT)

        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods))
        router.route().handler(BodyHandler.create()) // <3>

        initRouter()

        vertx.createHttpServer().requestHandler(router).listen(8080)
        router.errorHandler(500) { routerContext ->
            logger.error { routerContext.failure().message }
            routerContext.error("error" to routerContext.failure().message)
        }

    }

    protected abstract fun initRouter()
}
