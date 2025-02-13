package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.UUID;

@Getter @Setter
public class Product {
    private String productId;

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    private String productName;

    @NotNull(message = "Product quantity cannot be null")
    @Min(value = 0, message = "Product quantity cannot be negative")
    private int productQuantity;


    public Product() {
        this.productId = UUID.randomUUID().toString();
    }
}