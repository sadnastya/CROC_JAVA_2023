package ru.croc.winter.nastyasad;

import java.util.function.Function;
import java.util.function.Predicate;

public class TernaryOperator {
    public static <T> InterfaceForFunction<T> operator(T argument, Predicate<T> predicate, InterfaceForFunction<T> func1, InterfaceForFunction<T> func2){
        if(predicate.test(argument)){
            return func1;
        }
        else {return func2;}
    }

    public static <T, R> R ternaryOperator(T argument, Predicate<T> predicate, Function<T, R> Function1, Function<T, R> Function2) {
        return (predicate.test(argument) ? Function1.apply(argument) : Function2.apply(argument));
    }
}
