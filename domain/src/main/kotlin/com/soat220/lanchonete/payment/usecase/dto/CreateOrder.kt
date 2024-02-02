package com.soat220.lanchonete.payment.usecase.dto

class CreateOrder(
    val customer: Customer?,
    val orderItems: List<OrderItem>,
    val notes: String?
)