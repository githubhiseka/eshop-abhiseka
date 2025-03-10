package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(savedProduct.getProductId(), product.getProductId());
        assertEquals(savedProduct.getProductName(), product.getProductName());
        assertEquals(savedProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    // positive update/edit test
    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Bambang 2");
        productRepository.update(product);

        Product savedProduct = productRepository.findAll().next();
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
        assertEquals(product.getProductId(), savedProduct.getProductId());
    }

    @Test
    void testUpdateIfProductIdDoesNotMatch() {
        Product product1 = new Product();
        product1.setProductId("existing-id");
        product1.setProductName("Existing Product");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("new-id"); // Different ID
        product2.setProductName("Updated Product");
        product2.setProductQuantity(50);

        Product updatedProduct = productRepository.update(product2);

        assertNull(updatedProduct); // Product with "new-id" is not in the repository

        // Ensure original product remains unchanged
        Product savedProduct = productRepository.findAll().next();
        assertEquals("existing-id", savedProduct.getProductId());
        assertEquals("Existing Product", savedProduct.getProductName());
    }

    @Test
    void testUpdateIfProductIdIsNull() {
        Product product1 = new Product();
        product1.setProductId(null); // Null ID
        product1.setProductName("Null ID Product");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId(null);
        product2.setProductName("Updated Product");
        product2.setProductQuantity(50);

        assertDoesNotThrow(() -> productRepository.update(product2)); // Should not crash
    }

    // negative update/edit test
    @Test
    void testUpdateIfProductNotFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.update(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    // positive delete test
    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        productRepository.delete(product.getProductId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    // negative delete test
    @Test
    void testDeleteIfProductNotFound() {
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}