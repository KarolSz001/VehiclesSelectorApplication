package com.app.service.test;

import com.app.enums.Color;
import com.app.exception.MyUncheckedException;
import com.app.model.Car;
import com.app.service.CarService;
import com.app.service.DataLoaderService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestCarService {


    private DataLoaderService dataLoaderService = new DataLoaderService("TestJsonFilename.json");
    private CarService carService = new CarService(dataLoaderService);


    @Test
    @DisplayName("Test - shouldThrowExceptionForWrongArgInCarsCreatorMethod")
    public void shouldThrowExceptionForWrongArgInCarsCreatorMethod() {

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, () -> CarService.carsDataCreator(-10));
        Assertions.assertEquals(" wrong args in 'carsDataCreator' method ", e.getMessage());
    }

    @Test
    @DisplayName("Test - shouldReturnCorrectNumberOfCars")
    public void shouldReturnCorrectNumberOfCars() {

        List<Car> carsList = carService.findAllCars();
        Assertions.assertEquals(4, carsList.size());
    }

    @Test
    @DisplayName("Test - shouldReturnNotExpectedNumberOfCars")
    public void shouldReturnNotExpectedNumberOfCars() {

        List<Car> carsList = carService.findAllCars();
        Assertions.assertNotEquals(5, carsList.size());
    }

    @Test
    @DisplayName("Test - shouldThrowExceptionForWrongArgInSortMethod")
    public void shouldThrowExceptionForWrongArgInSortMethod() {

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, () -> carService.sort(null, true));
        Assertions.assertEquals(" null args in sort method ", e.getMessage());
    }

    @Test
    @DisplayName("Test  - shouldThrowExceptionForWrongArgInFilteredByPriceInRange")
    public void shouldThrowExceptionForWrongArgInFilteredByPriceInRange() {

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, () -> carService.filteredByPriceInRange(new BigDecimal(100), new BigDecimal(50)));
        Assertions.assertEquals(" Range is not correct in filteredByPriceInRange method ", e.getMessage());
    }

    @Test
    @DisplayName("Test  - shouldReturnCorrectNumberOfCarsFromMethod")
    public void shouldReturnCorrectNumberOfCarsFromMethod() {
        List<Car> carList = carService.filteredByPriceInRange(new BigDecimal(0), new BigDecimal(10000000));
        Assertions.assertEquals(4, carList.size());
    }

    @Test
    @DisplayName("Test  - should valid all data in carList")
    public void shouldPassCorrectDataInList() {
        List<Car> carList = carService.findAllCars();
        final String regex = "[A-Z\\s]+";
        MatcherAssert.assertThat(carList, Matchers.everyItem(CarMatcher.matches(regex, new BigDecimal(100000), new BigDecimal(500000), 10000, 500000, regex)));
    }

    @Test
    @DisplayName("Test  - should throw Exception because of not valid Price")
    public void shouldThrowExceptionForNotValidData() {
        final String regex = "[A-Z]+";

        Car car1 = Car.builder().model("AA").color(Color.BLACK).mileage(40000).price(new BigDecimal(100000)).components(List.of("TV")).build();
        Car car2 = Car.builder().model("BB").color(Color.BLACK).mileage(50000).price(new BigDecimal(100000)).components(List.of("TV")).build();

        MatcherAssert.assertThat(car1, CarMatcher.matches(regex, new BigDecimal(100000), new BigDecimal(500000), 10000, 500000, regex));
        MatcherAssert.assertThat(car2, Matchers.hasProperty("model", Matchers.equalTo("BB")));
    }

    @Spy
    List<Car> carServiceListSpy = new ArrayList<>();

    @Test
    @DisplayName("Test  - spy car service list and test add method ")
    void test1() {

        carServiceListSpy.add(0, Car.builder().build());
        carServiceListSpy.add(1, Car.builder().build());
        Assertions.assertEquals(2, carServiceListSpy.size());
        Mockito.doReturn(100).when(carServiceListSpy).size();
        Assertions.assertEquals(100, carServiceListSpy.size());
    }

    @Mock
    List<Car> carServiceMock = new ArrayList<>();

    @Test
    @DisplayName("Test  - mock car service list and test add method ")
    void test2() {

        carServiceMock.add(0, Car.builder().build());
        carServiceMock.add(1, Car.builder().build());
        Assertions.assertEquals(0, carServiceMock.size());
        Mockito.when(carServiceMock.size()).thenReturn(100);
        Assertions.assertEquals(100, carServiceMock.size());
    }


}
