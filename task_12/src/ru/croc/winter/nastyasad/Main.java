package ru.croc.winter.nastyasad;
public class Main {
    public static void main(String[] args) {
        InterfaceForFunction ternary1 = TernaryOperator.operator("ffddddга", MyPredicates.longWord(), () -> "Выполнилась функция 1", () -> "Выполнилась функция 2" );
        System.out.println("Для длинного слова: "+ternary1.function());

        InterfaceForFunction ternary2 = TernaryOperator.operator("ffга", MyPredicates.longWord(), () -> "Выполнилась функция 1", () -> "Выполнилась функция 2" );
        System.out.println("Для короткого слова: "+ternary2.function());
    }
}