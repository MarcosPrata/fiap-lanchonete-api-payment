package com.soat220.lanchonete.payment.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result

interface ListProductsPort {
    fun execute(): Result<List<Product>, DomainException>
}