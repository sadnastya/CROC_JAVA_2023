package ru.croc.winter.nastyasad;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MenuPredicates {
    public static Predicate<Dish> hasCategory(String category) {
        return p -> p.getCategory().equalsIgnoreCase(category);
    }

    public static Predicate<Dish> beginWith(String firstLetter) {
        return p -> String.valueOf(p.getName().charAt(0)).equalsIgnoreCase(firstLetter);
    }

    public static Predicate<Dish> containsProduct(String product) {
        return p -> {
            for (String ingredient : p.getIngredients()) {
                if (product.equalsIgnoreCase(ingredient)) {
                    return true;
                }
            }
            return false;
        };
    }
}
