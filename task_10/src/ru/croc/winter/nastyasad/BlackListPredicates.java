package ru.croc.winter.nastyasad;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class BlackListPredicates {
    public static <T> Predicate<T>  simpleFilter(Collection<T> blackList){

        return p -> {
            for (T badWord : blackList) {
                if (p.equals(badWord)) {
                        return false;
                    }
                }
            return true;
        };
    }
}
