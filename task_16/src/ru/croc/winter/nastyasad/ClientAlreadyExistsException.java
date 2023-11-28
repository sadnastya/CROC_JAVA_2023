package ru.croc.winter.nastyasad;

public class ClientAlreadyExistsException extends Exception{
    public ClientAlreadyExistsException() {

    }

    @Override
    public String getMessage() {
        return "Клиент с таким номером телефона уже существует";
    }
}
