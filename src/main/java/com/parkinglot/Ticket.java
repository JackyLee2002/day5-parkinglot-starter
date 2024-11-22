package com.parkinglot;

public class Ticket {
    private boolean isUsed;

    Ticket(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    Ticket() {
        this.isUsed = false;
    }

    public void setUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }
}
