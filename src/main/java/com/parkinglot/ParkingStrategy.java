package com.parkinglot;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots) throws NoAvailablePositionException;
}