package ru.croc.winter.nastyasad;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;


public interface BlackListFilter {
    public default List<?> filterComments(Predicate<> predicate, Collection<?> collection, Set<String> blackList){
        List<?> newComments = collection.stream()
                .filter( predicate )
                .toList();
        return newComments;
    }
}