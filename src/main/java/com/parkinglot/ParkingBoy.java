package com.parkinglot;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//       if (availableParkingLot == null) {
//           throw new NoAvailablePositionException();
//       }
        return availableParkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        Car parkedCar = parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(ticket))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedParkingTicketException());
        return parkedCar;
    }

    public ParkingLot findParkingLotById(Integer parkingLotId) {
        return parkingLots.stream().filter(lot -> lot.getId() == parkingLotId).findFirst().orElse(null);
    }


}