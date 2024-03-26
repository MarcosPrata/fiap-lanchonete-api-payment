package com.soat220.lanchonete.payment.driven

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.payment.driven.postgresdb.PaymentRepository
import com.soat220.lanchonete.payment.driven.postgresdb.model.PaymentEntity
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import com.soat220.lanchonete.payment.port.FindPaymentByOrderPort
import com.soat220.lanchonete.payment.port.UpdatePaymentPort
import org.springframework.stereotype.Service

@Service
class UpdatePaymentAdapter(
    private val findPaymentByOrderPort: FindPaymentByOrderPort,
    private val paymentRepository: PaymentRepository
): UpdatePaymentPort {

    override fun execute(order: Long, paymentStatus: PaymentStatus): Result<Payment, DomainException> {
        val payment = findPaymentByOrderPort.execute(order).orThrow()

        payment.paymentStatus = paymentStatus

        return Success(paymentRepository.save(PaymentEntity.from(payment)).toDomain())
    }
}