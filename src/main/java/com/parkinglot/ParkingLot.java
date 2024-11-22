package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    public static final int DEFAULT_LOT_CAPACITY = 10;
    public static final String NO_AVAILABLE_POSITION_MESSAGE = "No available position.";

    private Integer capacity;

    Map<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_LOT_CAPACITY;
    }

    public Ticket park(Car car) {
        if (parkingRecords.size() >= capacity) {
            System.out.print(NO_AVAILABLE_POSITION_MESSAGE);
            return null;
        }
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

    public Integer getCapacity() {
        return capacity;
    }

    public Map<Ticket, Car> getParkingRecords() {
        return parkingRecords;
    }
}
