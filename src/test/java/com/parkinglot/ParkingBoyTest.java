package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE = "Unrecognized parking ticket.";
    public static final String NO_AVAILABLE_POSITION_MESSAGE = "No available position.";

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_boy() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
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
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot)));
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

    @Test
    void should_return_nothing_with_error_message_when_park_given_uncognized_ticket_and_a_parking_boy() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot)));
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        // When
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(wrongTicket), UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
    }

    @Test
    void should_return_nothing_with_error_message_when_park_given_used_ticket_and_a_parking_boy() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot)));
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        // Then
        Car fetchedCar = parkingBoy.fetch(ticket);
        assertNotNull(fetchedCar);
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket), UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
    }

    @Test
    void should_return_nothing_with_error_message_when_park_given_no_available_lot_and_a_parking_boy() throws Exception {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            Ticket ticket = parkingBoy.park(car);
        }
        // When
        // Then
        Car car2 = new Car();
        assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car2), NO_AVAILABLE_POSITION_MESSAGE);
    }

    @Test
    void should_returned_parked_cars_at_first_lot_when_park_given_a_parking_boy_and_a_car_and_two_lots() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
        assertTrue(parkingLot1.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_returned_parked_cars_at_second_lot_when_park_given_a_parking_boy_and_a_car_and_two_lots() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 0);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
        assertTrue(parkingLot2.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_returned_correct_parked_cars_at_second_lot_when_fetch_given_a_parking_boy_and_two_car_and_two_lots() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        // When
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);
        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_returned_nothing_with_err_message_when_fetch_given_a_parking_boy_and_two_lots_and_a_car_and_a_invalid_ticket() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car1 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket unrecognizedTicket = new Ticket();
        // When
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedTicket), UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
    }

    @Test
    void should_returned_nothing_with_err_message_when_fetch_given_a_parking_boy_and_two_lots_and_a_car_and_a_used_ticket() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car1 = new Car();
        Ticket ticket = parkingBoy.park(car1);
        Car fetchedCar = parkingBoy.fetch(ticket);
        // When
        // Then
        assertEquals(car1, fetchedCar);
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket), UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
    }

    @Test
    void should_returned_nothing_with_err_message_when_park_given_a_parking_boy_and_two_full_lots_and_a_car() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        parkingBoy.park(car1);
        parkingBoy.park(car2);
        // When
        // Then
        assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car3), NO_AVAILABLE_POSITION_MESSAGE);
    }


}
