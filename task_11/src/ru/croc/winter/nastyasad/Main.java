package ru.croc.winter.nastyasad;

import java.util.*;
import java.util.stream.Collectors;

import static ru.croc.winter.nastyasad.MenuPredicates.*;

public class Main {

    public static void main(String[] args) throws IncorrectGradeException {
        Dish[] dishesStartArray = {
                new Dish("Куриный бульон", "Первое", new String[]{"курица", "укроп"}, 10, 50),
                new Dish("Сырный суп", "Первое", new String[]{"плавленый сыр", "сливки", "курица", "картофель"}, 70, 40),
                new Dish("Паста Карбонара", "Второе", new String[]{"бекон", "сыр", "сливки", "спагетти"}, 90, 60),
                new Dish("Плов с курицей", "Второе", new String[]{"рис", "морковь", "курица", "чеснок"}, 55, 50),
                new Dish("Лазанья", "Второе", new String[]{"мясной фарш", "помидоры", "листы для лазаньи", "сливки", "морковь"}, 80, 90),
                new Dish("Тирамису", "Десерт", new String[]{"яйца", "какао", "мука", "сыр Маскарпоне"}, 30, 40),
                new Dish("Бананы в шоколаде", "Десерт", new String[]{"бананы", "шоколад"}, 55, 96)
        };

        WeekDays[] days1 = {WeekDays.Sunday, WeekDays.Tuesday, WeekDays.Monday, WeekDays.Thursday};
        WeekDays[] days2 = {WeekDays.Wednesday, WeekDays.Thursday, WeekDays.Monday, WeekDays.Friday};
        WeekDays[] days3 = {WeekDays.Friday, WeekDays.Sunday, WeekDays.Saturday, WeekDays.Wednesday};
        WeekDays[] days4 = {WeekDays.Tuesday, WeekDays.Friday, WeekDays.Monday, WeekDays.Wednesday};
        WeekDays[] days5 = {WeekDays.Tuesday, WeekDays.Thursday, WeekDays.Wednesday, WeekDays.Saturday};

        Cook[] cooksStartArray = {
                new Cook("Повар1", Arrays.stream(days1).collect(Collectors.toSet())),
                new Cook("Повар2", Arrays.stream(days2).collect(Collectors.toSet())),
                new Cook("Повар3", Arrays.stream(days3).collect(Collectors.toSet())),
                new Cook("Повар4", Arrays.stream(days4).collect(Collectors.toSet())),
                new Cook("Повар5", Arrays.stream(days5).collect(Collectors.toSet()))
        };

        ArrayList<Dish> dishes = new ArrayList<Dish>();
        ArrayList<Cook> cooks = new ArrayList<Cook>();

        Collections.addAll(dishes, dishesStartArray);
        Collections.addAll(cooks, cooksStartArray);

        Kitchen kitchen = new Kitchen();

        HashSet<Dish> dishForCook0=new HashSet<>();
        dishForCook0.add(dishes.get(0));
        dishForCook0.add(dishes.get(3));

        kitchen.setCooksAndDishes(cooks.get(0), dishForCook0);

        HashSet<Dish> dishForCook1=new HashSet<>();
        dishForCook1.add(dishes.get(5));
        dishForCook1.add(dishes.get(4));
        kitchen.setCooksAndDishes(cooks.get(1), dishForCook1);

        HashSet<Dish> dishForCook2=new HashSet<>();
        dishForCook2.add(dishes.get(1));
        dishForCook2.add(dishes.get(3));
        kitchen.setCooksAndDishes(cooks.get(2), dishForCook2);

        HashSet<Dish> dishForCook3=new HashSet<>();
        dishForCook3.add(dishes.get(0));
        dishForCook3.add(dishes.get(5));
        dishForCook3.add(dishes.get(2));
        kitchen.setCooksAndDishes(cooks.get(3), dishForCook3);

        HashSet<Dish> dishForCook4=new HashSet<>();
        dishForCook4.add(dishes.get(3));
        dishForCook4.add(dishes.get(2));
        dishForCook4.add(dishes.get(6));
        kitchen.setCooksAndDishes(cooks.get(4), dishForCook4);


        System.out.println();
        ArrayList<String> stopProducts = new ArrayList<>();
        stopProducts.add("рис");


        System.out.println(kitchen.createMenu(WeekDays.Wednesday, stopProducts, 2));

        System.out.println();
        WeekDays[] days = {WeekDays.Sunday, WeekDays.Thursday, WeekDays.Monday, WeekDays.Saturday};
        Cook newCook = new Cook("Новый повар", Arrays.stream(days).collect(Collectors.toSet()));

        HashSet<Dish> dishesForNewCook = new HashSet<>();
        dishesForNewCook.add(dishes.get(5));
        dishesForNewCook.add(dishes.get(2));

        kitchen.addNewCook(newCook, dishesForNewCook);
        System.out.println();
        System.out.println("Фильтруем по тем же критериям + только категория Второе");
        System.out.println(kitchen.createMenu(hasCategory("Второе"), WeekDays.Wednesday, stopProducts, 3));

        System.out.println();
        System.out.println("Фильтруем по тем же критериям + начинается с буквы П");
        System.out.println(kitchen.createMenu(beginWith("П"), WeekDays.Wednesday, stopProducts, 3));

        System.out.println();
        System.out.println("Фильтруем по тем же критериям + содержит в составе сливки");
        System.out.println(kitchen.createMenu(containsProduct("сливки"), WeekDays.Wednesday, stopProducts, 3));
    }
}