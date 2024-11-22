package com.parkinglot;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    protected  List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot()));
    }

    public ParkingLot getAvailabeParkingLot() throws NoAvailablePositionException {
        ParkingLot resultParkingLot = parkingLots.stream().filter(parkingLot -> !parkingLot.isParkingLotFull()).findFirst()
                .orElse(null);
        if (resultParkingLot == null) {
            throw new NoAvailablePositionException();
        } else {
            return resultParkingLot;
        }
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

    public ParkingLot findParkingLotById(Integer parkingLotId) {
        return parkingLots.stream().filter(lot -> Objects.equals(lot.getId(), parkingLotId)).findFirst().orElse(null);
    }


}