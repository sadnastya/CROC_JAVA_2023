package ru.croc.winter.nastyasad;

import java.time.LocalDate;

public class IncorrectGradeException extends Exception {

    @Override
    public String getMessage() {
            return "Оценка блюда должна быть от 0 до 100";
        }

}
