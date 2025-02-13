package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findById(String productId) {
        // Find a product by its ID using the repository
        Iterator<Product> productIterator = productRepository.findAll();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Return null if the product is not found
    }

    @Override
    public Product update(Product updatedProduct) {
        // Update the product using the repository
        Product product = findById(updatedProduct.getProductId());
        if (product != null) {
            // Update the product's details
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
            productRepository.update(product); // Assuming the repository has an update method
        }
        return product;
    }

    @Override
    public void delete(String productId) {
        // Delete the product using the repository
        productRepository.delete(productId); // Assuming the repository has a delete method
    }
}