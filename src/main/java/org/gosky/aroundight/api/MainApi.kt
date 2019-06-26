package org.gosky.aroundight.api

import io.vertx.reactivex.ext.mongo.MongoClient
import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.RoutingContext
import mu.KotlinLogging
import org.gosky.aroundight.ext.success
import org.gosky.aroundight.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * @Auther: guozhong
 * @Date: 2019-05-21 11:20
 * @Description:
 */
private val logger = KotlinLogging.logger {}

@Component
class MainApi : BaseApi {


    @Autowired
    private lateinit var mongo: MongoClient

    @Autowired
    private lateinit var orderService: OrderService

    override fun initRouter(router: Router) {
        router.post("/order").handler { createOrder(it) }

        router.get("/order-list").handler(::getOrderList)

        router.put("/order/paid").handler { orderStatusPaid(it) }
    }

    /**
     * 生成订单
     */
    private fun createOrder(context: RoutingContext) {

        val money = context.bodyAsJson.getString("money")

        orderService.createOrder(money.toBigDecimal())
                .subscribe { t ->
                    context.success(t)
                }

    }

    /**
     * 获取订单列表
     */
    private fun getOrderList(context: RoutingContext) {
        orderService.getOrderList()
                .subscribe { t ->
                    context.success(t)
                }
    }

    /**
     * 修改支付状态 => 已支付
     */
    private fun orderStatusPaid(context: RoutingContext) {

        val randomMoney = context.bodyAsJson.getDouble("randomMoney")

        orderService.notifyPaid(randomMoney)
                .subscribe { t: String? ->
                    context.success(t)
                }

    }


}
