package com.soat220.lanchonete.payment.port

import com.soat220.lanchonete.common.model.Order

interface ProcessPaymentPort {

    fun execute(order: Order, totalAmount: Double): Boolean
}