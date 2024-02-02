package com.soat220.lanchonete.payment.port

import com.soat220.lanchonete.payment.model.Payment

interface HandlePaymentPort {
    fun execute(orderId: Long, totalAmount: Double): Payment
}