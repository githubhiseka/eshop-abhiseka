package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private BindingResult result;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        assertEquals("createProduct", viewName); // ensure the view name is correct
        verify(model).addAttribute(eq("product"), any(Product.class)); // ensure "product" is added to model
    }

    @Test
    void testProductListPage() {
        // create mock products
        Product product1 = new Product();
        product1.setProductId("id-1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("id-2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);

        // make it as a list
        List<Product> mockProducts = Arrays.asList(product1, product2);

        when(productService.findAll()).thenReturn(mockProducts);

        // call the controller method
        String viewName = productController.productListPage(model);

        // verify expected results
        assertEquals("productList", viewName); // ensure view name is right
        verify(productService).findAll(); // ensure service.findAll() is called
        verify(model).addAttribute("products", mockProducts); // ensure model contains product list
    }

}