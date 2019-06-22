package org.gosky.aroundight.verticle

import mu.KotlinLogging
import org.springframework.stereotype.Component

/**
 * @Auther: guozhong
 * @Date: 2019-06-18 19:13
 * @Description:
 */
private val logger = KotlinLogging.logger {}

@Component
class UserVerticle : RestVerticle() {
    override fun initRouter() {

    }
}