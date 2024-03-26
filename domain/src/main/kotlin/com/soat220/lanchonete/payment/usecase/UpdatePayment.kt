package com.soat220.lanchonete.payment.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import com.soat220.lanchonete.payment.port.UpdatePaymentPort
import javax.inject.Named

@Named
class UpdatePayment(
    private val updatePaymentPort: UpdatePaymentPort
) {

    fun execute(orderId: Long, paymentStatus: PaymentStatus): Result<Payment, DomainException> {
        return updatePaymentPort.execute(orderId, paymentStatus)
    }

}