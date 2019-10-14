package com.app.service;

import com.app.exception.MyUncheckedException;
import com.app.model.Car;
import com.app.utilities.CarsStoresJsonConverter;

import java.util.List;

public class DataLoaderService {

    private final CarsStoresJsonConverter carsJson;


    public DataLoaderService(final String jsonFilename) {
        if (jsonFilename == null) {
            throw new MyUncheckedException(" null file args for DataLoaderService constructor ");
        }
        carsJson = new CarsStoresJsonConverter(jsonFilename);
    }

    public List<Car> loadData() {
        return carsJson.fromJson().get();
    }

    public void saveToFile(int numberOfCars) {

        if (numberOfCars <= 0) {
            throw new MyUncheckedException(" wrong args for saveToFile ");
        }
        List<Car> carList = CarService.carsDataCreator(numberOfCars);
        carsJson.toJson(carList);
    }


}
