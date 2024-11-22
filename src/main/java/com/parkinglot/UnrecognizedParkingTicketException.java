package com.parkinglot;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public static final String UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE = "Unrecognized parking ticket.";

    public UnrecognizedParkingTicketException(String message) {
        super(UNRECOGNIZED_PARKING_TICKET_ERROR_MESSAGE);
    }
}
