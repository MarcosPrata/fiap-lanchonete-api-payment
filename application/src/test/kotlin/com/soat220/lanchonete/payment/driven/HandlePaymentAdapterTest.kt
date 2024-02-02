import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.port.FindOrderByIdPort
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.PaymentStatus
import com.soat220.lanchonete.payment.port.CreatePaymentPort
import com.soat220.lanchonete.payment.port.ProcessPaymentPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class HandlePaymentAdapterTest {

    // Mock the ports
    private val findOrderByIdPort: FindOrderByIdPort = mock(FindOrderByIdPort::class.java)
    private val createPaymentPort: CreatePaymentPort = mock(CreatePaymentPort::class.java)
    private val processPaymentPort: ProcessPaymentPort = mock(ProcessPaymentPort::class.java)

    // Create an instance of HandlePaymentAdapter with the mocked ports
    private val handlePaymentAdapter = HandlePaymentAdapter(findOrderByIdPort, createPaymentPort, processPaymentPort)

    @Test
    fun `execute should return a successful payment when payment is approved`() {
        // Arrange
        val orderId = 1L
        val totalAmount = 100.0
        val order = Order(/* populate with necessary data */)

        `when`(findOrderByIdPort.execute(orderId)).thenReturn(Success(order))
        `when`(processPaymentPort.execute(order, totalAmount)).thenReturn(true)
        `when`(createPaymentPort.execute(order, PaymentStatus.APPROVED, totalAmount)).thenReturn(Success(Payment(/* populate with necessary data */)))

        // Act
        val result: Payment = handlePaymentAdapter.execute(orderId, totalAmount)

        // Assert
        assertEquals(PaymentStatus.APPROVED, result.status) // Validate payment status
        // Add more assertions based on your implementation and business logic
    }

    @Test
    fun `execute should throw an exception when payment is declined`() {
        // Arrange
        val orderId = 2L
        val totalAmount = 150.0
        val order = Order(/* populate with necessary data */)

        `when`(findOrderByIdPort.execute(orderId)).thenReturn(Success(order))
        `when`(processPaymentPort.execute(order, totalAmount)).thenReturn(false)

        // Act and Assert
        // You can use assertThrows to verify that the expected exception is thrown
        assertThrows<PaymentNotApprovedException> {
            handlePaymentAdapter.execute(orderId, totalAmount)
        }
    }

    // Add more test cases to cover various scenarios, such as edge cases and exception handling
}
