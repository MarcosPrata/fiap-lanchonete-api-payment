package com.soat220.lanchonete.payment.driver.rest

import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.payment.usecase.ListProducts
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/payment"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PaymentController(
    private val listProducts: ListProducts,
) {
    @GetMapping("/products")
    fun findProducts() = listProducts.execute().orThrow()
}