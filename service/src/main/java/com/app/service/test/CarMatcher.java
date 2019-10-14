package com.app.service.test;

import com.app.model.Car;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.math.BigDecimal;

public class CarMatcher extends TypeSafeMatcher<Car> {

    private String modelRegex;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int minMileage;
    private int maxMileage;
    private String componentRegex;

    private boolean isCarOK = true;
    private boolean isCarModelOk = true;
    private boolean isCarPriceOK = true;
    private boolean isCarMileageOk = true;
    private boolean isCarComponentOK = true;

    public CarMatcher(String modelRegex, BigDecimal minPrice, BigDecimal maxPrice, int minMileage, int maxMileage, String componentRegex) {
        this.modelRegex = modelRegex;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minMileage = minMileage;
        this.maxMileage = maxMileage;
        this.componentRegex = componentRegex;
    }

    @Override
    protected boolean matchesSafely(Car car) {
        if (car == null) {
            isCarOK = false;
            return false;
        }
        if (car.getModel() == null || !car.getModel().matches(modelRegex)) {
            isCarModelOk = false;
        }
        if (car.getPrice().compareTo(minPrice) < 0 || car.getPrice().compareTo(maxPrice) > 0) {
            isCarPriceOK = false;
        }
        if (car.getMileage() < minMileage || car.getMileage() > maxMileage) {
            isCarMileageOk = false;
        }
        if (!car.getComponents().stream().allMatch(f -> f.matches(componentRegex))) {
            isCarComponentOK = false;
        }
        return isCarModelOk && isCarPriceOK && isCarMileageOk && isCarComponentOK;
    }

    @Override
    public void describeTo(Description description) {

        if (!isCarOK) {
            description.appendText("\n car is null");
        }
        if (!isCarModelOk) {
            description.appendText("\n car model is not valid");
        }
        if (!isCarPriceOK) {
            description.appendText("\n car price is not valid");
        }
        if (!isCarMileageOk) {
            description.appendText("\n car mileage is not valid");
        }
    }

    public static CarMatcher matches(final String modelRegex, final BigDecimal minPrice, final BigDecimal maxPrice, final int minMileage, final int maxMileage, final String componentRegex) {
        return new CarMatcher(modelRegex, minPrice, maxPrice, minMileage, maxMileage, componentRegex);
    }
}
