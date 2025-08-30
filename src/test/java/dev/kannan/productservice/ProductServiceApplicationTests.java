package dev.kannan.productservice;

import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	/* custom test*/
	@Test
	public void testAddition() {
		/*
			3A framework
			A -> Arrange
			A -> Act
			A -> Assert
		*/

		//arrange
		int a = 5;
		int b = 7;
		int result = a + b;

		assertEquals(12, result); //result should be equal to 12 (to pass)
		assertNull(null); //obj is null (to pass)
		assertNotNull(1); //obj is not null (to pass)
		assertNotEquals(1, 2); //both are not equal (to pass)
		assertTimeout(Duration.ofMillis(1000), () -> productRepository.findById(10L)); // function should return something withing 1000 ms (to pass)
		assertThrows(ProductNotFoundException.class, () -> productRepository.findById(-1L));
		


	}

}
