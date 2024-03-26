package com.soat220.lanchonete.payment.model

import com.soat220.lanchonete.common.model.Order
import java.time.LocalDateTime
import java.util.Objects

class Payment(

    val id: Long?,
    val order: Long,
    var paymentStatus: PaymentStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val totalAmount: Double

) {}