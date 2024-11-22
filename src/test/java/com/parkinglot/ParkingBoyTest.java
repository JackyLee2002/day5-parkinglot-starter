package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_boy() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket =parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_a_ticket_and_a_parking_boy() throws Exception {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        Car fetchedCar = parkingBoy.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_parked_cars_when_fetch_given_a_parking_boy_and_two_cars_and_two_tickets() throws Exception {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Car car2 = new Car();
        Ticket ticket2 = parkingBoy.park(car2);
        // When
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);
        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }



}
