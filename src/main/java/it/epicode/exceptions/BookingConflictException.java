package it.epicode.exceptions;

public class BookingConflictException extends RuntimeException {
    public BookingConflictException(){}

    public BookingConflictException(String message) {
        super(message);
    }
}
