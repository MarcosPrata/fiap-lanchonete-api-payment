import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.payment.driven.postgresdb.PaymentRepository
import com.soat220.lanchonete.payment.driven.postgresdb.model.PaymentEntity
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CreatePaymentAdapterTest {

    // Mock the PaymentRepository
    private val paymentRepository: PaymentRepository = mock(PaymentRepository::class.java)

    // Create an instance of CreatePaymentAdapter with the mocked PaymentRepository
    private val createPaymentAdapter = CreatePaymentAdapter(paymentRepository)

    @Test
    fun `execute should return a Success result with the created payment`() {
        // Arrange
        val order = Order(/* populate with necessary data */)
        val paymentStatus = PaymentStatus.PAID
        val totalAmount = 100.0

        `when`(paymentRepository.save(Mockito.any(PaymentEntity::class.java)))
            .thenReturn(PaymentEntity(/* populate with necessary data */))

        // Act
        val result: Result<Payment, DomainException> =
            createPaymentAdapter.execute(order, paymentStatus, totalAmount)

        // Assert
        assertEquals(Success::class.java, result.javaClass) // Check if the result is a Success
        assertEquals(paymentStatus, result.getOrThrow().status) // Validate the payment status
        // Add more assertions based on your implementation and business logic
    }

    @Test
    fun `execute should return a Failure result when an exception occurs during payment creation`() {
        // Arrange
        val order = Order(/* populate with necessary data */)
        val paymentStatus = PaymentStatus.PAID
        val totalAmount = 100.0

        `when`(paymentRepository.save(Mockito.any(PaymentEntity::class.java)))
            .thenThrow(RuntimeException("Failed to save payment"))

        // Act
        val result: Result<Payment, DomainException> =
            createPaymentAdapter.execute(order, paymentStatus, totalAmount)

        // Assert
        assertEquals(Failure::class.java, result.javaClass) // Check if the result is a Failure
        // Add more assertions based on your implementation and business logic for failure cases
    }

    // Add more test cases to cover various scenarios, such as edge cases and different payment statuses
}
