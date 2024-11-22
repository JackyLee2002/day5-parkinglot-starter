package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {

    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE = "Unrecognized parking ticket.";
    public static final String NO_AVAILABLE_POSITION_MESSAGE = "No available position.";

    @Test
    void should_return_lot_two_parked_cars_when_park_given_two_lots_a_smart_parking_boy_and_a_car() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 100);
        ParkingLot parkingLot2 = new ParkingLot(2, 100);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1)), new SmartParkingStrategy());
        ParkingBoy superParkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)), new SuperParkingStrategy());
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);
        Ticket ticket1 = superParkingBoy.park(car2);
        // When
        // Then
        assertTrue(parkingLot2.getParkingRecords().containsKey(ticket1));
    }

    @Test
    void should_return_lot_one_parked_cars_when_park_given_two_lots_a_smart_parking_boy_and_a_car() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 100);
        ParkingLot parkingLot2 = new ParkingLot(2, 100);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot2)), new SmartParkingStrategy());
        ParkingBoy superParkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)), new SuperParkingStrategy());
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);
        Ticket ticket1 = superParkingBoy.park(car2);
        // When
        // Then
        assertTrue(parkingLot1.getParkingRecords().containsKey(ticket1));
    }

    @Test
    void should_return_lot_one_parked_cars_when_park_given_two_lots_equally_free_a_smart_parking_boy_and_a_car() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 100);
        ParkingLot parkingLot2 = new ParkingLot(2, 100);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)), new SmartParkingStrategy());
        ParkingBoy superParkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)), new SuperParkingStrategy());
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        parkingBoy.park(car1);
        parkingBoy.park(car2);
        Ticket ticket1 = superParkingBoy.park(car3);
        // When
        // Then
        assertTrue(parkingLot1.getParkingRecords().containsKey(ticket1));
    }

    @Test
    void should_returned_nothing_with_err_message_when_fetch_given_a_parking_boy_and_two_lots_and_a_car_and_a_invalid_ticket() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)), new SuperParkingStrategy());
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
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)), new SuperParkingStrategy());
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
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)), new SuperParkingStrategy());
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
