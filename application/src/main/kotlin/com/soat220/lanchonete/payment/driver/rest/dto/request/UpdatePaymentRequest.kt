package com.soat220.lanchonete.payment.driver.rest.dto.request

import com.soat220.lanchonete.payment.model.PaymentStatus

class UpdatePaymentRequest (
    val orderId: Long,
    val paymentStatus: PaymentStatus
)