package com.app.utilities;

import com.app.model.Car;

import java.util.List;


public class CarsStoresJsonConverter extends JsonConverter<List<Car>> {
    public CarsStoresJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
