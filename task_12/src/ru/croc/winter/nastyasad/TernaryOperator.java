package ru.croc.winter.nastyasad;

import java.util.function.Predicate;

public class TernaryOperator {
    public static <T> InterfaceForFunction operator(T argument, Predicate<T> predicate, InterfaceForFunction func1, InterfaceForFunction func2){
        if(predicate.test(argument)){
            return func1;
        }
        else {return func2;}
    }
}
