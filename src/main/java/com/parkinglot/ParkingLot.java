package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {

    private Integer size;

    HashMap<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(Integer size) {
        this.size = size;
    }

    public ParkingLot() {
        this.size = 10;
    }

    public Ticket park(Car car) {
        if (parkingRecords.size() >= size) return null;
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return parkingRecords.remove(ticket);
    }
}
