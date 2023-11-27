package ru.croc.winter.nastyasad;

import java.util.function.Predicate;

import static ru.croc.winter.nastyasad.TernaryOperator.ternaryOperator;

public class Main {
    public static void main(String[] args) {
        InterfaceForFunction<String> ternary1 = TernaryOperator.operator("ffddddга", MyPredicates.longWord(), () -> "Выполнилась функция 1", () -> "Выполнилась функция 2" );
        System.out.println("Для длинного слова: "+ternary1.function());

        InterfaceForFunction<String> ternary2 = TernaryOperator.operator("ffга", MyPredicates.longWord(), () -> "Выполнилась функция 1", () -> "Выполнилась функция 2" );
        System.out.println("Для короткого слова: "+ternary2.function());

        ternaryOperator(-10, n -> {n < 0}, Math::abs, Math::sqrt);
    }
}