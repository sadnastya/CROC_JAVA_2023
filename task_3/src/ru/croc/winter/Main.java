package ru.croc.winter;

import java.util.Scanner;

public class Main {
    public static boolean Prime(long number) {
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        if (number < 1) {
            return false;
        }
        if (number == 1 || number == 2) {
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter an integer: ");
        if (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            if (Prime(number)) {
                System.out.print("Prime number. ");
                if (Prime(number - 2)) {
                    System.out.print("Twin prime is " + (number - 2));
                } else if (Prime(number + 2)) {
                    System.out.print("Twin prime is " + (number + 2));
                }
            } else {
                System.out.print("Composite number");
            }
        } else {
            System.out.println("It is not integer");
        }
        System.out.println();
    }
}