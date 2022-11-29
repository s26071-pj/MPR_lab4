package pl.pjatk.mpr_lab4.Service;
import org.springframework.stereotype.Service;
import pl.pjatk.mpr_lab4.Exception.DatabaseException;
import pl.pjatk.mpr_lab4.Exception.ValidationException;
import pl.pjatk.mpr_lab4.Model.Car;
import pl.pjatk.mpr_lab4.Repository.CarRepository;

import java.util.Optional;

@Service
public class RentalService {


    private final CarRepository carRepository;

    public RentalService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public void rentCar() {
        //TODO
    }

    public void addNewCar(Car car) {
        if (isInvalid(car.getVin())) {
            throw new ValidationException("VIN is required!");
        }
        if (isInvalid(car.getMake())) {
            throw new ValidationException("Make is required!");
        }

        try {
            carRepository.addCar(car);
        } catch (Exception e) {
            throw new DatabaseException("Database error: ", e);
        }
    }

    public Optional<Car> findCarById(int id) {
        return carRepository.findCarById(id);
    }

    private boolean isInvalid(String attribute) {
        return attribute == null || attribute.isBlank();
    }
}