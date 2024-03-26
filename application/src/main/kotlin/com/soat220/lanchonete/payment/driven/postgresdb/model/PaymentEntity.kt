package com.soat220.lanchonete.payment.driven.postgresdb.model

import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "payment")
class PaymentEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(name = "order_id")
    private val order: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private val paymentStatus: PaymentStatus,

    @Column(name = "total_amount")
    private val totalAmount: Double,

    private val createdAt: LocalDateTime,

    private val updatedAt: LocalDateTime

) {

    fun toDomain() = Payment(
        id = id,
        order = order,
        createdAt = createdAt,
        paymentStatus = paymentStatus,
        totalAmount = totalAmount,
        updatedAt = updatedAt
    )

    companion object {
        fun from(order: Long, paymentStatus: PaymentStatus, totalAmount: Double) = PaymentEntity(
            order = order,
            paymentStatus = paymentStatus,
            totalAmount = totalAmount,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        fun from(payment: Payment) = PaymentEntity(
            id = payment.id,
            order = payment.order,
            paymentStatus = payment.paymentStatus,
            createdAt = payment.createdAt,
            totalAmount = payment.totalAmount,
            updatedAt = LocalDateTime.now()
        )
    }
}