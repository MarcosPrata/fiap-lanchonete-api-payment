package com.soat220.lanchonete.payment.model

enum class PaymentStatus(code: Int) {

    APPROVED(0),
    DECLINED(1),
    ERROR(2)

}