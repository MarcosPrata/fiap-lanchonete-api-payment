package com.soat220.lanchonete.payment.driven

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import com.soat220.lanchonete.payment.port.ProcessPaymentPort
import com.soat220.lanchonete.payment.port.SendPaymentConfirmedQueuePort
import com.soat220.lanchonete.payment.port.UpdatePaymentPort
import org.springframework.stereotype.Service

@Service
class ProcessPaymentAdapter(
    private val updatePaymentPort: UpdatePaymentPort,
    private val sendPaymentConfirmedQueuePort: SendPaymentConfirmedQueuePort
): ProcessPaymentPort {

    override fun execute(orderId: Long, paymentStatus: PaymentStatus): Result<Payment, DomainException> {

        return try {
            val payment = updatePaymentPort.execute(orderId, paymentStatus).orThrow()

            if (PaymentStatus.APPROVED == paymentStatus) {
                sendPaymentConfirmedQueuePort.execute(orderId.toString())
            }

            Success(payment)
        } catch (e: Exception) {
            return Failure(DomainException(e))
        }
    }
}