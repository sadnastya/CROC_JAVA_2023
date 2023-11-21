package ru.croc.winter.nastyasad;

public class LotSoldException extends Exception {
    @Override
    public String getMessage() {
        return "Лот продан. Торги закрыты";
    }
}
