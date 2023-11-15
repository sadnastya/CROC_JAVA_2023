package ru.croc.winter.nastyasad;
public class Main {
    public static void main(String[] args) {
        OurFunctions function = TernaryOperator.operator("книга", MyPredicates.longWord(), () -> "Выполнилась функция 1", () -> "Выполнилась функция 2" );
        System.out.println(function);
    }
}