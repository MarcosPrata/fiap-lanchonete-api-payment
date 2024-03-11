package com.soat220.lanchonete.payment.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.payment.model.Payment

interface FindPaymentByOrderPort {

    fun execute(orderId: Long): Result<Payment, DomainException>

}