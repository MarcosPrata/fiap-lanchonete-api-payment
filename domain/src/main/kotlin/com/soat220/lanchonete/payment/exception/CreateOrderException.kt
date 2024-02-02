package com.soat220.lanchonete.payment.exception

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Order

class CreateOrderException(
    message: String
) : DomainException(
    message = message,
    errorCode = ErrorCode.CREATE_ORDER_ERROR
) {
    constructor(
        order: Order?,
        details: List<DomainException>
    ) : this(order.toString()) {
        this.details = details
    }
}