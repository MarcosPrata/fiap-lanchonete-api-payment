package com.soat220.lanchonete.payment.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.port.FindPaymentByOrderPort
import javax.inject.Named

@Named
class FindPaymentByOrder(
    private val findPaymentByOrderPort: FindPaymentByOrderPort
) {

    fun execute(orderId: Long): Result<Payment, DomainException> {
        return findPaymentByOrderPort.execute(orderId)
    }

}