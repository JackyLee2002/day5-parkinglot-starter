package com.parkinglot;


import java.util.*;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public SmartParkingBoy() {
        this.parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot()));
    }

    @Override
    public ParkingLot getAvailabeParkingLot() throws NoAvailablePositionException {
            ParkingLot bestLot = parkingLots.stream()
                    .max(Comparator.comparingInt(ParkingLot::getNumberOfFreePositions))
                    .orElse(null);
        if (bestLot == null) {
            throw new NoAvailablePositionException();
        } else {
            return bestLot;
        }
    }

    public Ticket park(Car car) {
       ParkingLot availableParkingLot = getAvailabeParkingLot();
        return availableParkingLot.park(car);
    }


}