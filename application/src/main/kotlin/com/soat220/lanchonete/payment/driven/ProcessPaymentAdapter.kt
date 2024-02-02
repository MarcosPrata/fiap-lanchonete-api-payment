package com.soat220.lanchonete.payment.driven

import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.payment.port.ProcessPaymentPort
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ProcessPaymentAdapter(): ProcessPaymentPort {

    override fun execute(order: Order, totalAmount: Double): Boolean {
        return Random.nextBoolean()
    }
}