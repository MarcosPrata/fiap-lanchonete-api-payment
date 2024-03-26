package com.soat220.lanchonete.payment.driven.postgresdb

import com.soat220.lanchonete.payment.driven.postgresdb.model.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PaymentRepository : JpaRepository<PaymentEntity, Long> {

    fun findByOrder(orderId: Long): Optional<PaymentEntity>
}