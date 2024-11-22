package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {

    private Integer capacity;

    HashMap<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = 10;
    }

    public Ticket park(Car car) {
        if (parkingRecords.size() >= capacity) return null;
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return parkingRecords.remove(ticket);
    }
}
