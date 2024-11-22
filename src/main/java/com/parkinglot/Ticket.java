package com.parkinglot;

public class Ticket {
    private boolean isUsed;

    private Integer parkingLotId;

    Ticket(Integer parkingLotId,Boolean isUsed) {
        this.isUsed = isUsed;
        this.parkingLotId = parkingLotId;
    }

    Ticket() {
        this.isUsed = false;
    }

    public void setUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
