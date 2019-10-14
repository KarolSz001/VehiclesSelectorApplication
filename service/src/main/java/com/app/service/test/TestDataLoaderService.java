package com.app.service.test;

import com.app.exception.MyUncheckedException;
import com.app.service.DataLoaderService;
import com.app.utilities.CarsStoresJsonConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)

public class TestDataLoaderService {

    private final String fileName = "TestJsonFilename.json";
    private final CarsStoresJsonConverter carsJson = new CarsStoresJsonConverter(fileName);

    @Test
    @DisplayName("Test -> shouldThrowExceptionForNullArgumentInConstructor")
    public void shouldThrowExceptionForNullArgumentInConstructor() {
        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, () -> new DataLoaderService(null));
        Assertions.assertEquals(" null file args for DataLoaderService constructor ", e.getMessage());
    }

    @Test
    @DisplayName("Test -> shouldThrowExceptionForWrongArgInMethod")
    public void shouldThrowExceptionForWrongArgInMethod() {
        DataLoaderService dataLoaderService = new DataLoaderService("test.json");
        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, () -> dataLoaderService.saveToFile(-4));
        Assertions.assertEquals(" wrong args for saveToFile ", e.getMessage());
    }
    @Test
    @DisplayName("Test -> shouldThrowExceptionForNullArgument")
    public void shouldThrowExceptionForNullArgument() {
        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class,()->carsJson.toJson(null));
        Assertions.assertEquals( "TO JSON EXCEPTION",e.getMessage());
    }

    @Test
    @DisplayName("Test -> shouldThrowExceptionForNullFile")
    public void shouldThrowExceptionForNullFile() {
        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, ()-> new CarsStoresJsonConverter(null));
        Assertions.assertEquals( "null argument for jsonFile",e.getMessage());
    }
}
