package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product existingProduct;

    @BeforeEach
    void setUp() {
        existingProduct = new Product();
        existingProduct.setProductId("valid-id");
        existingProduct.setProductName("Test Product");
        existingProduct.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("new-id");
        product.setProductName("New Product");
        product.setProductQuantity(50);

        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertNotNull(result); // product cannot be null
        assertEquals("new-id", result.getProductId()); // ensure ID is the same
        assertEquals("New Product", result.getProductName()); // ensure name is the same
        assertEquals(50, result.getProductQuantity()); // ensure quantity is the same

        verify(productRepository).create(product); // ensure repository create() is called
    }

    // when the repository has multiple products
    @Test
    void testFindAllWhenProductsExist() {
        Product product1 = new Product();
        product1.setProductId("id-1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("id-2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);

        // mocking productRepository.findAll() to return these products
        Iterator<Product> iterator = List.of(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result); // ensure the result is not null
        assertEquals(2, result.size()); // ensure both products are retrieved
        assertEquals("id-1", result.get(0).getProductId()); // ensure correct order
        assertEquals("id-2", result.get(1).getProductId());
    }

    // when the repository is empty
    @Test
    void testFindAllWhenRepositoryIsEmpty() {
        Iterator<Product> emptyIterator = List.<Product>of().iterator();
        when(productRepository.findAll()).thenReturn(emptyIterator);

        List<Product> result = productService.findAll();

        assertNotNull(result); // ensure result is not null
        assertTrue(result.isEmpty()); // ensure the list is empty
    }


    // when the repository is empty (while loop never runs)
    @Test
    void testFindByIdWhenRepositoryIsEmpty() {
        Iterator<Product> emptyIterator = List.<Product>of().iterator();
        when(productRepository.findAll()).thenReturn(emptyIterator);

        Product result = productService.findById("some-id");

        assertNull(result); // should be null since there are no products
    }

    // when no product matches the given ID (while loop runs, but if condition is always false)
    @Test
    void testFindByIdWhenNoMatchingProduct() {
        Product nonMatchingProduct = new Product();
        nonMatchingProduct.setProductId("wrong-id");
        nonMatchingProduct.setProductName("Other Product");

        Iterator<Product> iterator = List.of(nonMatchingProduct).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        Product result = productService.findById("valid-id");

        assertNull(result); // no matching ID so should be null
    }

    // when a product matches the given ID (if condition is true)
    @Test
    void testFindByIdWhenProductExists() {
        Iterator<Product> iterator = List.of(existingProduct).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        Product result = productService.findById("valid-id");

        assertNotNull(result); // should be a valid product
        assertEquals("valid-id", result.getProductId());
        assertEquals("Test Product", result.getProductName());
    }

    // when the product exists (if condition is true)
    @Test
    void testUpdateWhenProductExists() {
        Product existingProduct = new Product();
        existingProduct.setProductId("valid-id");
        existingProduct.setProductName("Old Product");
        existingProduct.setProductQuantity(100);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("valid-id");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(200);

        Iterator<Product> iterator = List.of(existingProduct).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        Product result = productService.update(updatedProduct);

        assertNotNull(result); // product should be found
        assertEquals("Updated Product", result.getProductName()); // ensure name is updated
        assertEquals(200, result.getProductQuantity()); // ensure quantity is updated

        verify(productRepository).update(existingProduct); // ensure repository update is called
    }

    // when the product does not exist (if condition is false)
    @Test
    void testUpdateWhenProductDoesNotExist() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existing-id");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(200);

        Iterator<Product> emptyIterator = List.<Product>of().iterator();
        when(productRepository.findAll()).thenReturn(emptyIterator);

        Product result = productService.update(updatedProduct);

        assertNull(result); // should return null when product is not found
        verify(productRepository, never()).update(any()); // ensure update() is not called
    }

    @Test
    void testDeleteProduct() {
        String productId = "delete-id";

        // call delete method
        productService.delete(productId);

        // verify that productRepository.delete() was called with correct ID
        verify(productRepository).delete(productId);
    }
}
