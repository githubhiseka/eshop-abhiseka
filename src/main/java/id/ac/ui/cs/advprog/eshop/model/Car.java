package id.ac.ui.cs.advprog.eshop.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car {
    private String carId;

    @NotBlank(message = "Car name cannot be blank")
    private String carName;

    @NotBlank(message = "Car color cannot be blank")
    private String carColor;

    @NotNull(message = "Car quantity cannot be null")
    @Min(value = 0, message = "Car quantity cannot be negative")
    private int carQuantity;
}
