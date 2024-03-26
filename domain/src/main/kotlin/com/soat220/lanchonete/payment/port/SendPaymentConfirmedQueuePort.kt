package com.soat220.lanchonete.payment.port

interface SendPaymentConfirmedQueuePort {

    fun execute(orderId: String)

}