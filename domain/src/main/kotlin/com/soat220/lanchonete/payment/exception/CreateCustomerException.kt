package com.soat220.lanchonete.payment.exception

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode

class CreateCustomerException(
    message: String
) : DomainException(
    message = message,
    errorCode = ErrorCode.CREATE_CUSTOMER_ERROR
) {
    constructor(
        name: String?,
        details: List<DomainException>
    ) : this(name ?: "") {
        this.details = details
    }
}