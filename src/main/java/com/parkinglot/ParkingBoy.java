package com.parkinglot;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ParkingBoy {
    protected  ParkingLot parkingLot = new ParkingLot();

    public Ticket park(Car car) {
        return parkingLot.park(car);
    }
}