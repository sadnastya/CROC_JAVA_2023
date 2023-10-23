
package ru.croc.winter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter a start number, a progression step and number of members: ");

        Scanner scanner2 = new Scanner(System.in);

        Scanner scanner3 = new Scanner(System.in);


        if (scanner1.hasNextInt() && scanner2.hasNextInt() && scanner3.hasNextInt()) {
            int a_start = scanner1.nextInt();
            int d = scanner2.nextInt();
            int number = scanner3.nextInt();
            int summa = 0;

            for (int i = 0; i < number; i++) {
                summa += a_start + d * i;
            }
            System.out.println("Result = " + summa);
        }
        else {
            System.out.print("Incorrect input");
        }
    }
}