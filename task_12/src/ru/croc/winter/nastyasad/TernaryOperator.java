package ru.croc.winter.nastyasad;

import java.util.function.Predicate;

public class TernaryOperator {
    public static OurFunctions operator(String argument, Predicate<String> predicate, InterfaceForFunction1 func1, InterfaceForFunction2 func2){
        if(predicate.test(argument)){
            return func1;
        }
        else {return func2;}
    }
}
