package ru.croc.winter.nastyasad;

import java.util.Arrays;
import java.util.Comparator;

public class Dish {
    protected String name;
    protected String category;
    protected String[] ingredients;
    protected int gradeKing;
    protected int gradeCourtiers;

    protected Dish(String name, String category, String[] ingredients, int gradeKing, int gradeCourtiers) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.gradeKing = gradeKing;
        this.gradeCourtiers = gradeCourtiers;
    }

    @Override
    public String toString() {
        return "Название: " + name + "\n Категория: " + category + "\n Состав: " +
                Arrays.toString(ingredients) + "\n Оценка короля\\оценка придворных: " + gradeKing + "\\" + gradeCourtiers;
    }

    public int getGradeKing() {
        return gradeKing;
    }

    public int getGradeCourtiers() {
        return gradeCourtiers;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

}
