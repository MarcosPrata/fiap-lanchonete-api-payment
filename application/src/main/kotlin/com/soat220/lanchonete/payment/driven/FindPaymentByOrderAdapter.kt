package com.soat220.lanchonete.payment.driven

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.payment.driven.postgresdb.PaymentRepository
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.port.FindPaymentByOrderPort
import org.springframework.stereotype.Service

@Service
class FindPaymentByOrderAdapter(
    private val paymentRepository: PaymentRepository
): FindPaymentByOrderPort {

    override fun execute(orderId: Long): Result<Payment, DomainException> {

        return try {
            val payment = paymentRepository.findByOrder(orderId)
            Success(payment.get().toDomain())
        } catch (e: Exception) {
            return Failure(DomainException(e))
        }
    }
}