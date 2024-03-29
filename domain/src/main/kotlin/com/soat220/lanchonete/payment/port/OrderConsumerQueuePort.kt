package com.soat220.lanchonete.payment.port

interface OrderConsumerQueuePort {

    fun receive(message: String)

}