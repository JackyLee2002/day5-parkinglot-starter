package com.parkinglot;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingStrategy parkingStrategy;

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = new DefaultParkingStrategy();
    }

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot()));
        this.parkingStrategy = new DefaultParkingStrategy();
    }

    public ParkingLot getAvailabeParkingLot() throws NoAvailablePositionException {
        return parkingStrategy.getAvailableParkingLot(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot availableParkingLot = getAvailabeParkingLot();
        return availableParkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> Objects.equals(ticket.getParkingLotId(), parkingLot.getId()))
                .map(parkingLot -> parkingLot.fetch(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
    }
}