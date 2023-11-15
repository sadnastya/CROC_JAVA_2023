package ru.croc.winter.nastyasad;

import java.util.ArrayList;

@FunctionalInterface
public interface WhoWorks {
    ArrayList<Cook> whoWorksToday(WeekDays day);
}
