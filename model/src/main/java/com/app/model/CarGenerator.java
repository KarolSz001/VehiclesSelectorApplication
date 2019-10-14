package com.app.model;


import com.app.exception.MyUncheckedException;
import com.app.model.valid.CarValidatorImpl;


import com.app.enums.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CarGenerator {

    //String model, BigDecimal price, Color color, int mileage, List<Component> components
    public static Car carGenerator() {
        CarValidatorImpl carValidator = new CarValidatorImpl();
        String model = modelGenerator();
        BigDecimal price = priceGenerator();
        Color color = Color.colorGenerator();
        int mileage = mileageGenerator();
        List<String> components = componentsListCreator();
        Car car = Car.builder().model(model).price(price).color(color).mileage(mileage).components(components).build();
        carValidator.validate(car);

        if (carValidator.hasErrors()) {
            System.out.println(" list of errors with validation of car ");
            carValidator.getErrors().forEach((k, v) -> System.out.println(k + "::::::::::" + v));
            throw new MyUncheckedException("car is not validate");
        }
        
        return Car.builder().model(model).price(price).color(color).mileage(mileage).components(components).build();
    }

    private static String modelGenerator() {
        String[] models = {"AUDI", "TESLA", "HONDA", "SCODA"};
        return models[new Random().nextInt(models.length)];
    }

    private static BigDecimal priceGenerator() {
        return new BigDecimal(new Random().nextInt(500000 - 100000 + 1) + 100000);
    }

    private static int mileageGenerator() {
        return new Random().nextInt(500000 - 100000 + 1) + 100000;
    }

    private static String componentGenerator() {
        List<String> list = Arrays.asList("TV", "ABS", "RADIO", "GPS");
        return list.get(new Random().nextInt(list.size()));

    }

    private static List<String> componentsListCreator() {
        List<String> componentList = new ArrayList<>();
        int counter = 0;
        while (counter < 3) {
            String component = componentGenerator();
            if (!componentList.contains(component)) {
                componentList.add(component);
                counter++;
            }
        }
        return componentList;
    }

}
