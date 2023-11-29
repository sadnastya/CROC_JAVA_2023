package ru.croc.winter.nastyasad;

public class LotNotSoldException extends Exception {
    @Override
    public String getMessage() {
        return "Лот еще не продан. Победитель не определен";
    }
}
