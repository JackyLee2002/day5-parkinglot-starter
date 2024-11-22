package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE = "Unrecognized parking ticket.";
    public static final String NO_AVAILABLE_POSITION_MESSAGE = "No available position.";

    @Test
    void should_return_ticket_when_park_given_a_car() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_a_ticket() throws Exception {
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
    void should_return_correct_parked_cars_when_fetch_given_two_cars_and_two_tickets() throws Exception {
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
    void should_return_nothing_when_fetch_given_wrong_ticket() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        // When
        Car fetchedCar = null;
        try {
            fetchedCar = parkingLot.fetch(wrongTicket);
        } catch (Exception ignored) {
        }
        // Then
        assertEquals(null, fetchedCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_used_ticket() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        Car fetchedAgainCar = null;
        try {
            fetchedAgainCar = parkingLot.fetch(ticket);
        } catch (Exception ignored) {
        }
        // Then
        assertEquals(car, fetchedCar);
        assertNull(fetchedAgainCar);
    }

    @Test
    void should_return_nothing_when_park_given_no_available_lot() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < parkingLot.getCapacity(); i++) {
            Car car = new Car();
            Ticket ticket = parkingLot.park(car);
            parkingLot.getParkingRecords().put(ticket, car);
        }
        // When
        Car car2 = new Car();
        Ticket ticket2 = null;
        try {
            ticket2 = parkingLot.park(car2);
        } catch (Exception ignored) {
        }
        // Then
        assertNull(ticket2);
    }

    @Test
    void should_return_nothing_with_error_message_when_park_given_no_available_lot() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < parkingLot.getCapacity(); i++) {
            Car car = new Car();
            Ticket ticket = parkingLot.park(car);
            parkingLot.getParkingRecords().put(ticket, car);
        }
        // When
        // Then
        Car car2 = new Car();
        var exception = assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(car2), NO_AVAILABLE_POSITION_MESSAGE);
    }


    @Test
    void should_return_nothing_with_error_message_when_park_given_uncognized_ticket() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        // When
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(wrongTicket), UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
    }

    @Test
    void should_return_nothing_with_error_message_when_park_given_used_ticket() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        // Then
        Car fetchedCar = parkingLot.fetch(ticket);
        assertNotNull(fetchedCar);
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(ticket), UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);

    }


}
