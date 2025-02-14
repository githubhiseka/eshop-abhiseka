package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

// jakarta validation api
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// product ID is gonna use UUID for extra safety
import java.util.UUID;

@Getter @Setter
public class Product {
    private String productId;

    // some validations for product name
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    private String productName;

    // validations for product quantity
    @NotNull(message = "Product quantity cannot be null")
    @Min(value = 0, message = "Product quantity cannot be negative")
    private int productQuantity;


    public Product() {
        // generate a unique UUID and put it as a string
        this.productId = UUID.randomUUID().toString();
    }
}