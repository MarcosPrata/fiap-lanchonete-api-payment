package com.soat220.lanchonete.payment.driver.rest

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.payment.driver.rest.dto.request.UpdatePaymentRequest
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.usecase.ProcessPayment
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/payment"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PaymentController(
    private val processPayment: ProcessPayment
) {

    @PostMapping
    fun updatePayment(@RequestBody updatePaymentRequest: UpdatePaymentRequest): Payment {
        return processPayment.execute(updatePaymentRequest.orderId, updatePaymentRequest.paymentStatus).orThrow()
    }

}