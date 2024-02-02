package com.soat220.lanchonete.payment.driver.rest.dto.request

import com.soat220.lanchonete.payment.usecase.dto.OrderItem

class CreateOrderItemRequest(
    private val productId: Long,
    private val amount: Long,
) {
    fun toDomain() = OrderItem(
        productId = productId,
        amount = amount
    )
}