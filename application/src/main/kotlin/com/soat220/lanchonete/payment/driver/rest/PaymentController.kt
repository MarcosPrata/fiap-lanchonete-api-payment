package com.soat220.lanchonete.payment.driver.rest

import com.soat220.lanchonete.payment.driver.rest.dto.request.UpdatePaymentRequest
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
    fun updatePayment(@RequestBody updatePaymentRequest: UpdatePaymentRequest) =
        processPayment.execute(updatePaymentRequest.orderId, updatePaymentRequest.paymentStatus)
}