package com.soat220.lanchonete.payment.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus

interface ProcessPaymentPort {

    fun execute(orderId: Long, paymentStatus: PaymentStatus): Result<Payment, DomainException>
}