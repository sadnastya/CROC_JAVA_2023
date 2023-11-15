package ru.croc.winter.nastyasad;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class BlackListPredicates {
    public static Predicate<?>  simpleFilter(Set<String> blackList){

        return p -> {
            for (String badWord : blackList) {
                if (p.toString().equalsIgnoreCase(badWord)) {
                    return false;
                }
            }
            return true;
        };
    }
}
