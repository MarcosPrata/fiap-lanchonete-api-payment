import com.soat220.lanchonete.common.model.Order
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ProcessPaymentAdapterTest {

    // Create an instance of ProcessPaymentAdapter
    private val processPaymentAdapter = ProcessPaymentAdapter()

    @Test
    fun `execute should return true when payment is approved`() {
        // Arrange
        val order = Order(/* populate with necessary data */)

        // Mock the Random.nextBoolean() to always return true
        val randomMock = mock(Random::class.java)
        `when`(randomMock.nextBoolean()).thenReturn(true)

        // Inject the mock into the adapter
        val processPaymentAdapter = ProcessPaymentAdapter(randomMock)

        // Act
        val result: Boolean = processPaymentAdapter.execute(order, 100.0)

        // Assert
        assertTrue(result) // Payment should be approved
    }

    @Test
    fun `execute should return false when payment is declined`() {
        // Arrange
        val order = Order(/* populate with necessary data */)

        // Mock the Random.nextBoolean() to always return false
        val randomMock = mock(Random::class.java)
        `when`(randomMock.nextBoolean()).thenReturn(false)

        // Inject the mock into the adapter
        val processPaymentAdapter = ProcessPaymentAdapter(randomMock)

        // Act
        val result: Boolean = processPaymentAdapter.execute(order, 150.0)

        // Assert
        assertFalse(result) // Payment should be declined
    }

    // Add more test cases to cover various scenarios, such as edge cases and different order amounts
}
