package ru.croc.winter.nastyasad;

import java.util.Comparator;

public class GradeDishComparator implements Comparator<Dish> {
    @Override
    public int compare(Dish o1, Dish o2) {
        if(o1.getGradeKing()!= o2.getGradeKing())
            return o1.getGradeKing() - o2.getGradeKing();
        else {
            return o1.getGradeCourtiers()- o2.getGradeCourtiers();
        }
    }
}
