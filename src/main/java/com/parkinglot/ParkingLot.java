package com.parkinglot;

import java.util.HashMap;
import java.util.Objects;

public class ParkingLot {

    public static final int DEFAULT_LOT_CAPACITY = 10;

    private Integer capacity;

    HashMap<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_LOT_CAPACITY;
    }

    public Ticket park(Car car) {
        if (parkingRecords.size() >= capacity) return null;
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = parkingRecords.remove(ticket);
        if (Objects.isNull(car)) return null;
        ticket.setUsed(true);
        return car;
    }
}
