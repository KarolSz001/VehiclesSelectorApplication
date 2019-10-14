package com.app.model.valid;

import com.app.model.Car;
import com.app.model.valid.generic.AbstractValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CarValidatorImpl extends AbstractValidator<Car> {

    private final String MODEL_REGEX = "[A-Z]+";
    private final String COMPONENT_REGEX = "[A-Z]+";
    private final BigDecimal MIN_PRICE = new BigDecimal(100000);
    private final Integer MIN_MILEAGE = 100000;


    public CarValidatorImpl() {
    }


    @Override
    public Map<String, String> validate(Car car) {

        if (!isModelCorrect(car.getModel())) {
            errors.put("Error nr 1", "Name only work with Letters");
        }
        if (!allComponentsCorrect(car.getComponents())) {
            errors.put("Error nr 2", "Surname only work with Letters");
        }
        if (!isPriceCorrect(car.getPrice())) {
            errors.put("Error nr 3", "Name is too short");
        }
        if (!isMileageCorrect(car.getMileage())) {
            errors.put("Error nr 4", "Surname is too short");
        }
        return errors;
    }

    private boolean isPriceCorrect(BigDecimal price) {
        return price.compareTo(MIN_PRICE) > 0;
    }

    private boolean isMileageCorrect(Integer mileage) {
        return mileage > MIN_MILEAGE;
    }

    private boolean allComponentsCorrect(List<String> components) {
        return components.stream().allMatch(f -> f != null && f.matches(COMPONENT_REGEX));
    }

    private boolean isModelCorrect(String model) {
        return model.matches(MODEL_REGEX);
    }

}
