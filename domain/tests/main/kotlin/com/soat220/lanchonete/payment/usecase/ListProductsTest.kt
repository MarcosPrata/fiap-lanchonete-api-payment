import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.port.FindProductsPort
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.orThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ListProductsTest {

    // Mock the FindProductsPort
    private val findProductsPort: FindProductsPort = mock(FindProductsPort::class.java)

    // Create an instance of ListProducts with the mocked FindProductsPort
    private val listProducts = ListProducts(findProductsPort)

    @Test
    fun `execute should return a list of products without deleted ones`() {
        // Arrange
        val products = listOf(
            Product(1, "Product 1", false),
            Product(2, "Product 2", true),
            Product(3, "Product 3", false)
        )

        `when`(findProductsPort.execute()).thenReturn(Success(products))

        // Act
        val result: Result<List<Product>, DomainException> = listProducts.execute()

        // Assert
        assertEquals(2, result.getOrThrow().size) // Only non-deleted products should be returned
    }

    @Test
    fun `execute should return an empty list if all products are deleted`() {
        // Arrange
        val products = listOf(
            Product(1, "Product 1", true),
            Product(2, "Product 2", true),
            Product(3, "Product 3", true)
        )

        `when`(findProductsPort.execute()).thenReturn(Success(products))

        // Act
        val result: Result<List<Product>, DomainException> = listProducts.execute()

        // Assert
        assertEquals(0, result.getOrThrow().size) // Empty list should be returned
    }

    // Add more test cases to cover various scenarios, such as error handling, exceptions, etc.
}
