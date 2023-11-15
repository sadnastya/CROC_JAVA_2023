package ru.croc.winter.nastyasad;

import java.util.function.Predicate;

public class MyPredicates {
    public static Predicate<String> longWord() {
        return word -> word.length()>5;
    }
}
