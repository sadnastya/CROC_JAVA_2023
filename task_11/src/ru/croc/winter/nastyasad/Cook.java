package ru.croc.winter.nastyasad;

import java.util.HashMap;
import java.util.Set;

public class Cook {
    public String name;
    public Set<WeekDays> workDays;

    public Cook(String cookName, Set<WeekDays> workDays) {
        this.name = cookName;
        this.workDays = workDays;
    }

    @Override
    public String toString() {
        return name;
    }
}
