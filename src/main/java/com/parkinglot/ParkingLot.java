package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    public static final int DEFAULT_LOT_CAPACITY = 10;
    public static final String NO_AVAILABLE_POSITION_MESSAGE = "No available position.";
    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE = "Unrecognized parking ticket.";

    private Integer capacity;

    private Integer id;

    Map<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(Integer id) {
        this.id = id;
        this.capacity = DEFAULT_LOT_CAPACITY;
    }

    public ParkingLot(Integer id, Integer capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_LOT_CAPACITY;
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        if (parkingRecords.size() >= capacity) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket(this.id);
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        if (ticket.isUsed() || !parkingRecords.containsKey(ticket)) {
            throw new UnrecognizedParkingTicketException();
        }
        Car car = parkingRecords.remove(ticket);
        ticket.setUsed(true);
        return car;
    }

    public boolean isParkingLotFull() {
        return parkingRecords.size() >= capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getId() {
        return id;
    }

    public Map<Ticket, Car> getParkingRecords() {
        return parkingRecords;
    }
}
