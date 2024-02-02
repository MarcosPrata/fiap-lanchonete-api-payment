package com.soat220.lanchonete.payment.driven.postgresdb.model

import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import java.time.LocalDateTime
import javax.persistence.*
import com.soat220.lanchonete.common.model.Order as DomainOrder

@Entity
class PaymentEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private val order: Order,

    @Enumerated(EnumType.STRING)
    private val paymentStatus: PaymentStatus,

    private val totalAmount: Double,

    private val createdAt: LocalDateTime

) {

    fun toDomain() = Payment(
        id = id,
        order = order.toDomain(),
        createdAt = createdAt,
        paymentStatus = paymentStatus,
        totalAmount = totalAmount
    )

    companion object {
        fun from(order: DomainOrder, paymentStatus: PaymentStatus, totalAmount: Double) = PaymentEntity(
            order = Order.fromDomain(order),
            paymentStatus = paymentStatus,
            createdAt = LocalDateTime.now(),
            totalAmount = totalAmount
        )
    }
}