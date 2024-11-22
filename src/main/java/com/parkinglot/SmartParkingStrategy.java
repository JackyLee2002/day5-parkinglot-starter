package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots) throws NoAvailablePositionException {
        return parkingLots.stream()
                .max(Comparator.comparingInt(ParkingLot::getNumberOfFreePositions))
                .orElseThrow(NoAvailablePositionException::new);
    }
}
