package org.gosky.aroundight.service

import io.reactivex.Single
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.reactivex.ext.auth.jwt.JWTAuth
import io.vertx.reactivex.ext.mongo.MongoClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Auther: guozhong
 * @Date: 2019-06-25 15:49
 * @Description:
 */

@Service
class UserService {

    @Autowired
    private lateinit var mongo: MongoClient

    @Autowired
    private lateinit var jwtAuth: JWTAuth

    fun login(username: String, password: String): Single<String> {

        return mongo
                .rxFind("user", json {
                    obj(
                            "username" to username,
                            "password" to password
                    )
                })
                .map(List<JsonObject>::first)
                .map {
                    jwtAuth.generateToken(json { obj(
                            "username" to it.getString("username")) })
                }

    }
}
