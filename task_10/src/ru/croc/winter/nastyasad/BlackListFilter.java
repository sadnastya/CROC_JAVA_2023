package ru.croc.winter.nastyasad;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;


public interface BlackListFilter {
    default <T> List<T> filterComments(Predicate<T> predicate, Collection<T> collection){
        List<T> newComments = new ArrayList<>();
        for (T objects : collection) {
            if (predicate.test(objects)) {
                newComments.add(objects);
            }
        }
        return newComments.reversed();
    }
}