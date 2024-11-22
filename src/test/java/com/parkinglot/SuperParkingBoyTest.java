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
        ParkingLot parkingLot1 = new ParkingLot(1,100);
        ParkingLot parkingLot2 = new ParkingLot(2,100);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1)));
        SmartParkingBoy superParkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
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
        ParkingLot parkingLot1 = new ParkingLot(1,100);
        ParkingLot parkingLot2 = new ParkingLot(2,100);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot2)));
        SmartParkingBoy superParkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);
        Ticket ticket1 = superParkingBoy.park(car2);
        // When
        // Then
        assertTrue(parkingLot1.getParkingRecords().containsKey(ticket1));
    }

}
