package org.gosky.aroundight.verticle

import io.vertx.reactivex.ext.web.RoutingContext
import mu.KotlinLogging
import org.gosky.aroundight.ext.success
import org.gosky.aroundight.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @Auther: guozhong
 * @Date: 2019-06-18 19:13
 * @Description:
 */
private val logger = KotlinLogging.logger {}

@Component
class UserVerticle : RestVerticle() {

    @Autowired
    private lateinit var userService: UserService

    override fun initRouter() {
        router.post("/user/login").handler(::login)
        val arrayList = ArrayList<Any>()
        arrayList.toList()
    }

    private fun login(context: RoutingContext) {
        val username = context.bodyAsJson.getString("username")
        val password = context.bodyAsJson.getString("password")
        userService.login(username, password)
                .subscribe { o ->
                    context.success(o)
                }
    }
}