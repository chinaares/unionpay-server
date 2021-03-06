package org.gosky.aroundight.ext

import com.google.gson.Gson
import io.vertx.core.json.JsonObject
import io.vertx.reactivex.ext.web.RoutingContext


/**
 * @Auther: guozhong
 * @Date: 2019-05-21 16:17
 * @Description:
 */


fun RoutingContext.success(any: Any?) {
    var toJson: String
    if (any is JsonObject) {
        toJson = any.toString()
    } else {
        toJson = Gson().toJson(any)
    }
    this.response()
            .putHeader("content-type", "application/json")
            .end(toJson)
}

fun RoutingContext.error(any: Any?) {
    this.request()
            .response()
            .putHeader("content-type", "application/json")
            .setStatusCode(500)
            .end(Gson().toJson(any))
}