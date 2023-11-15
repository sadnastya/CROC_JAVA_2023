package ru.croc.winter.nastyasad;

import java.util.ArrayList;
import static ru.croc.winter.nastyasad.MenuPredicates.*;

public class Main {
    public static void main(String[] args) {
        Kitchen kitchen = new Kitchen();

        System.out.println();
        ArrayList<String> stopProducts = new ArrayList<>();
        stopProducts.add("рис");


        System.out.println(kitchen.makeMenu(WeekDays.Sunday, stopProducts, 2));

        System.out.println();

        Cook newCook = new Cook("Новый повар", false, true, true, false, true, true, false);

        ArrayList<Dish> dishesForNewCook = new ArrayList<>();
        dishesForNewCook.add(kitchen.dishes.get(5));
        dishesForNewCook.add(kitchen.dishes.get(2));

        kitchen.addNewCook(newCook, dishesForNewCook);
        System.out.println();
        System.out.println("Фильтруем по тем же критериям + только категория Второе");
        System.out.println(kitchen.filterMenuWithPredicate(hasCategory("Второе"), WeekDays.Sunday, stopProducts, 3));

        System.out.println();
        System.out.println("Фильтруем по тем же критериям + начинается с буквы Б");
        System.out.println(kitchen.filterMenuWithPredicate(beginWith("Б"), WeekDays.Sunday, stopProducts, 3));

        System.out.println();
        System.out.println("Фильтруем по тем же критериям + содержит в составе сливки");
        System.out.println(kitchen.filterMenuWithPredicate(containsProduct("сливки"), WeekDays.Sunday, stopProducts, 1));

    }
}