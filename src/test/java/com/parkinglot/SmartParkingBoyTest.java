package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {

    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE = "Unrecognized parking ticket.";
    public static final String NO_AVAILABLE_POSITION_MESSAGE = "No available position.";

    @Test
    void should_return_lot_two_parked_cars_when_park_given_two_lots_a_smart_parking_boy_and_two_cars_and_two_tickets() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1,1);
        ParkingLot parkingLot2 = new ParkingLot(2,100);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car1 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        // When
        // Then
        assertTrue(parkingLot2.getParkingRecords().containsKey(ticket1));
    }

    @Test
    void should_return_lot_one_parked_cars_when_park_given_two_lots_a_smart_parking_boy_and_two_cars_and_two_tickets() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1,100);
        ParkingLot parkingLot2 = new ParkingLot(2,1);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car1 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        // When
        // Then
        assertTrue(parkingLot1.getParkingRecords().containsKey(ticket1));
    }
}
