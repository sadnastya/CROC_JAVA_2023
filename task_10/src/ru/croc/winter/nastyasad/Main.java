package ru.croc.winter.nastyasad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CharSequence[] blackList = new CharSequence[] {"лето", "небо"};


        ArrayList<String> comments = new ArrayList<String>() {};
        comments.add("Книга, Море: Солнце.");
        comments.add("Стол, стул" + "\n нЕбО");
        comments.add("Солнце;^ лето: зима");
        comments.add("Небо");
        comments.add("Мороженое............Песок");
        comments.add("книга");
        comments.add("весна");
        comments.add("осень");
        comments.add("лето");
        comments.add("мороженое");

        WordsFilter collect = new WordsFilter();
        System.out.println(collect.filterComments(BlackListPredicates.simpleFilter(blackList), comments));
        /*получается, что если я буду заносить SET в filterComments, то восстановить порядок не получится,
        потому что они лежат в "куче", а для любых других коллекций фильтр будет работать с правильным порядком*/
    }
}