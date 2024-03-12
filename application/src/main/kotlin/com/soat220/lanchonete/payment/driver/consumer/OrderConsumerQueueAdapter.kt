package com.soat220.lanchonete.payment.driver.consumer

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.OrderItem
import com.soat220.lanchonete.config.LocalDateTimeTypeAdapter
import com.soat220.lanchonete.payment.model.PaymentStatus
import com.soat220.lanchonete.payment.port.CreatePaymentPort
import com.soat220.lanchonete.payment.port.OrderConsumerQueuePort
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderConsumerQueueAdapter (
    private val createPaymentPort: CreatePaymentPort
) : OrderConsumerQueuePort {

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
        .create()

    @RabbitListener(queues = ["\${queue.pedidos.pagamento.pendente}"])
    override fun receive(@Payload message: String) {

        val order = gson.fromJson(message, Order::class.java)

        createPaymentPort.execute(order.id!!, PaymentStatus.PENDING, getAmount(order.orderItems))
    }

    private fun getAmount(orderItems: MutableList<OrderItem>): Double {
        return orderItems
            .map { it.product.price * it.amount }
            .reduce { acc, price -> acc + price }
    }
}