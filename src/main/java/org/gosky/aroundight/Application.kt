package org.gosky.aroundight

import io.vertx.kotlin.ext.auth.jwt.jwtAuthOptionsOf
import io.vertx.kotlin.ext.auth.pubSecKeyOptionsOf
import io.vertx.reactivex.core.Vertx
import io.vertx.reactivex.ext.auth.jwt.JWTAuth
import org.gosky.aroundight.verticle.RestVerticle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import javax.annotation.PostConstruct


@SpringBootApplication
class Application {

    @Autowired
    private lateinit var vertx: Vertx

    @Autowired
    private lateinit var restVerticle: RestVerticle

    @PostConstruct
    fun deployVerticle() {
        vertx.deployVerticle(restVerticle)
    }

    @Bean
    fun vertx(): Vertx {
        return Vertx.vertx()
    }

    @Bean
    fun jwt(): JWTAuth {
        val provider = JWTAuth.create(vertx, jwtAuthOptionsOf(
                pubSecKeys = listOf(pubSecKeyOptionsOf(
                        algorithm = "HS256",
                        publicKey = "keyboard cat",
                        symmetric = true))))
        return provider
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

