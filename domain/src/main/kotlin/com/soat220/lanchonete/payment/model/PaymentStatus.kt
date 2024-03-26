package com.soat220.lanchonete.payment.model

enum class PaymentStatus(code: Int) {

    APPROVED(0),
    PENDING(1),
    DECLINED(2),
    ERROR(3)

}