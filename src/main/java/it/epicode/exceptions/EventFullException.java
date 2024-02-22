package it.epicode.exceptions;

public class EventFullException extends RuntimeException {

    public EventFullException() {
    }

    public EventFullException(String message) {
        super(message);
    }
}