package com.soat220.lanchonete.payment.driven

import com.soat220.lanchonete.common.port.FindOrderByIdPort
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import com.soat220.lanchonete.payment.port.CreatePaymentPort
import com.soat220.lanchonete.payment.port.HandlePaymentPort
import com.soat220.lanchonete.payment.port.ProcessPaymentPort
import org.springframework.stereotype.Service

@Service
class HandlePaymentAdapter(
    private val findOrderByIdPort: FindOrderByIdPort,
    private val createPaymentPort: CreatePaymentPort,
    private val processPaymentPort: ProcessPaymentPort,
    //private val addToQueuePort: AddToQueuePort
) : HandlePaymentPort {

    override fun execute(orderId: Long, totalAmount: Double): Payment {

        val order = findOrderByIdPort.execute(orderId).orThrow()

        val paymentStatus =
            if (processPaymentPort.execute(order, totalAmount)) PaymentStatus.APPROVED
            else PaymentStatus.DECLINED

        val payment = createPaymentPort.execute(order, paymentStatus, totalAmount).orThrow()

//        if (paymentStatus == PaymentStatus.APPROVED) {
//            addToQueuePort.execute(order)
//        } else {
//            throw PaymentNotApprovedException("Payment not approved", ErrorCode.PAYMENT_NOT_APPROVED)
//        }

        return payment
    }
}