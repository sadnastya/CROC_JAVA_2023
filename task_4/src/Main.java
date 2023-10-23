package ru.croc.winter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a_start, d, number;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a start: ");
        if (scanner.hasNextInt()) {
            int a_start = scanner.nextInt();}
        else {
            System.out.print("Incorrect input")
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a progression step : ");
        if (scanner.hasNextInt()) {
            int d = scanner.nextInt();}
        else {
            System.out.print("Incorrect input")
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();}
        else {
            System.out.print("Incorrect input")
        }
    }
}