package ru.croc.winter.nastyasad;

import java.time.LocalDate;

public class ExpirationException extends Exception {
    private final LocalDate dateExpired;

    public ExpirationException(LocalDate date) {
        this.dateExpired = date;
    }

    @Override
    public String getMessage() {
        return "Истек срок хранения заказа: " + dateExpired;
    }
}
