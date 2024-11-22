package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    void should_return_ticket_when_park_given_a_car() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_a_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_parked_cars_when_fetch_given_two_cars_and_two_tickets() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Ticket ticket1 = parkingLot.park(car1);
        Car car2 = new Car();
        Ticket ticket2 = parkingLot.park(car2);
        // When
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);
        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_nothing_when_fetch_given_wrong_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        // When
        Car fetchedCar = parkingLot.fetch(wrongTicket);
        // Then
        assertEquals(null, fetchedCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        Car fetchedAgainCar = parkingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
        assertNull(fetchedAgainCar);
    }

    @Test
    void should_return_nothing_when_park_given_no_available_lot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        for(int i =0; i < parkingLot.getCapacity();i++) {
            Car car = new Car();
            Ticket ticket = parkingLot.park(car);
            parkingLot.getParkingRecords().put(ticket, car);
        }
        // When
        Car car2 = new Car();
        Ticket ticket2 = parkingLot.park(car2);
        // Then
        assertNull(ticket2);
    }

    @Test
    void should_return_nothing_with_error_message_when_park_given_no_available_lot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        for(int i =0; i < parkingLot.getCapacity();i++) {
            Car car = new Car();
            Ticket ticket = parkingLot.park(car);
            parkingLot.getParkingRecords().put(ticket, car);
        }
        // When
        Car car2 = new Car();
        Ticket ticket2 = parkingLot.park(car2);
        // Then
        assertNull(ticket2);
        String expectedErrorMessage = "No available position.";
        assertEquals(systemOut(), expectedErrorMessage);
    }




}
