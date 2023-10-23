
package ru.croc.winter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter a start: ");
        if (scanner1.hasNextInt()) {
            int a_start = scanner1.nextInt();}
        else {
            System.out.print("Incorrect input")
        }

        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Enter a progression step : ");
        if (scanner2.hasNextInt()) {
            int d = scanner2.nextInt();}
        else {
            System.out.print("Incorrect input")
        }

        Scanner scanner3 = new Scanner(System.in);
        System.out.print("Enter a number: ");
        if (scanner3.hasNextInt()) {
            int number = scanner3.nextInt();}
        else {
            System.out.print("Incorrect input")
        }


        for


    }
}