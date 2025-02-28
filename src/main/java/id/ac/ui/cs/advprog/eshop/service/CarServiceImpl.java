package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create (Car car) {
        if (car == null || car.getCarId() == null || car.getCarId().isEmpty()) {
            throw new IllegalArgumentException("Car ID cannot be null or empty.");
        }
        return carRepository.create(car);
    }

    @Override
    public List <Car> findAll() {
        Iterator <Car> carIterator = carRepository.findAll();
        List <Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById (String carId) {
        if (carId == null || carId.isEmpty()) {
            throw new IllegalArgumentException("Car ID cannot be null or empty.");
        }
        Car car = carRepository.findById(carId);
        if (car == null) {
            throw new RuntimeException("Car not found with ID: " + carId);
        }
        return car;
    }

    @Override
    public void update (String carId, Car car) {
        if (carId == null || carId.isEmpty()) {
            throw new IllegalArgumentException("Car ID cannot be null or empty.");
        }
        Car existingCar = carRepository.findById(carId);
        if (existingCar == null) {
            throw new RuntimeException("Car with ID " + carId + " does not exist.");
        }
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById (String carId) {
        if (carId == null || carId.isEmpty()) {
            throw new IllegalArgumentException("Car ID cannot be null or empty.");
        }
        Car existingCar = carRepository.findById(carId);
        if (existingCar == null) {
            throw new RuntimeException("Car with ID " + carId + " does not exist.");
        }
        carRepository.delete(carId);
    }
}
