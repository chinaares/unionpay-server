package org.gosky.aroundight

import io.vertx.reactivex.core.Vertx
import org.gosky.aroundight.verticle.MainVerticle
import org.gosky.aroundight.verticle.UserVerticle
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
    private lateinit var apiVerticle: MainVerticle
    @Autowired
    private lateinit var userVerticle: UserVerticle

    @PostConstruct
    fun deployVerticle() {
        vertx.deployVerticle(apiVerticle)
        vertx.deployVerticle(userVerticle)
    }

    @Bean
    fun vertx(): Vertx {
        return Vertx.vertx()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

