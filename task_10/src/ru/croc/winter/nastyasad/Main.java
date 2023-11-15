package ru.croc.winter.nastyasad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> blackList = new HashSet<>();
        blackList.add("Море");
        blackList.add("Небо");

        Set<String> comments = new HashSet<>();
        comments.add("Книга, Море: Солнце.");
        comments.add("Стол, стул" + "\n нЕбО");
        comments.add("Солнце;^ лето: зима");
        comments.add("Небо");
        comments.add("Мороженое............Песок");


        WordsFilter collect = new WordsFilter();
        System.out.println(collect.filterComments(BlackListPredicates.simpleFilter(blackList), comments));
    }
}