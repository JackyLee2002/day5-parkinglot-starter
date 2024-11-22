package com.parkinglot;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots) throws NoAvailablePositionException {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isParkingLotFull())
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
    }
}