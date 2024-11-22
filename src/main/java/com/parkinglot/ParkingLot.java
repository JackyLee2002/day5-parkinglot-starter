package com.parkinglot;

import java.util.HashMap;
import java.util.Objects;

public class ParkingLot {

    HashMap<Ticket, Car> parkingRecords = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car fetchedCar = parkingRecords.get(ticket);
        if (Objects.nonNull(fetchedCar)) parkingRecords.remove(ticket);
        return fetchedCar;
    }
}
