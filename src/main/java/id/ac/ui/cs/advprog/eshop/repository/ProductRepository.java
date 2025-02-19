package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product update(Product product) {
        // iterate productData and search for matching ID
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId() != null && productData.get(i).getProductId().equals(product.getProductId())) {
                // update the product
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }

    public void delete(String productId) {
        // remove the product with the matching ID
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}