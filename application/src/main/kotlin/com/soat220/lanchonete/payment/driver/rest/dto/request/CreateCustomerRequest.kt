package com.soat220.lanchonete.payment.driver.rest.dto.request

import com.soat220.lanchonete.payment.usecase.dto.Customer

class CreateCustomerRequest(
    val cpf: String,
    val name: String,
    val email: String,
) {
    fun toDomain() = Customer(
        cpf = cpf,
        name = name,
        email = email
    )
}