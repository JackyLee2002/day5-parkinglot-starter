package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots) throws NoAvailablePositionException {
        return parkingLots.stream()
                .max(Comparator.comparingDouble(ParkingLot::getPercentageOfFreePositions))
                .orElseThrow(NoAvailablePositionException::new);
    }
}