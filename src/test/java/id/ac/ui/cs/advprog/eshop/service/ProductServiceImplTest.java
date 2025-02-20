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
