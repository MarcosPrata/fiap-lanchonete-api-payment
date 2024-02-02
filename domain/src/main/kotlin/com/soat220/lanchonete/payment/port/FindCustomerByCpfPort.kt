package com.soat220.lanchonete.payment.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Result

interface FindCustomerByCpfPort {

    fun execute(cpf: String?): Result<Customer?, DomainException>
}