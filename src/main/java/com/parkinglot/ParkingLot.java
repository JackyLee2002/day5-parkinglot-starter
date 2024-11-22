package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    public static final int DEFAULT_LOT_CAPACITY = 10;
    public static final String NO_AVAILABLE_POSITION_MESSAGE = "No available position.";
    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE = "Unrecognized parking ticket.";

    private Integer capacity;

    Map<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_LOT_CAPACITY;
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        if (parkingRecords.size() >= capacity) {
            throw new NoAvailablePositionException(NO_AVAILABLE_POSITION_MESSAGE);
        }
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        if (ticket.isUsed()) {
            throw new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
        }
        Car car = parkingRecords.remove(ticket);
        if (Objects.isNull(car)) {
            throw new UnrecognizedParkingTicketException (UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
        }
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
