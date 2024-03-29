package com.soat220.lanchonete.payment.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import com.soat220.lanchonete.payment.port.ProcessPaymentPort
import javax.inject.Named

@Named
class ProcessPayment(
    private val processPaymentPort: ProcessPaymentPort
) {

    fun execute(orderId: Long, paymentStatus: PaymentStatus): Result<Payment, DomainException> {
        return processPaymentPort.execute(orderId, paymentStatus)
    }
}