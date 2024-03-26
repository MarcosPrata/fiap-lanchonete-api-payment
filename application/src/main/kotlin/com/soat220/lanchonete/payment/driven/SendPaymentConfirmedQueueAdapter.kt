package com.soat220.lanchonete.payment.driven

import com.soat220.lanchonete.payment.port.SendPaymentConfirmedQueuePort
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SendPaymentConfirmedQueueAdapter(

    private val rabbitTemplate: RabbitTemplate,
    @Value("\${queue.pedidos.confirmados}") private val queue: String

) : SendPaymentConfirmedQueuePort {

    override fun execute(orderId: String) {
        rabbitTemplate.convertAndSend(queue, orderId)
    }
}