package com.parkinglot;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public SuperParkingBoy() {
        this.parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot()));
    }

    @Override
    public ParkingLot getAvailabeParkingLot() throws NoAvailablePositionException {
            ParkingLot bestLot = parkingLots.stream()
                    .max(Comparator.comparingDouble(parkingLot -> parkingLot.getPercentageOfFreePositions()))
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