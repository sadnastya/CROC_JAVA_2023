package ru.croc.winter.nastyasad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        WordsFilter cleanComments = new WordsFilter();
        List<String> comments = new ArrayList<>();
        comments.add("Книга, Море: Солнце.");
        comments.add("Стол, стул" + "\n Небо");
        comments.add("Солнце;^ лето: зима");
        comments.add("Небо");
        comments.add("Мороженое............Песок");

        Set<String> blackList = new HashSet<>();
        blackList.add("Море");
        blackList.add("Небо");
        cleanComments.filterComments(comments, blackList);

        System.out.println("Комментарии до применения фильтра: ");
        System.out.println(comments + "\n");
        System.out.println("Комментарии после применения фильтра: ");
        System.out.println(cleanComments.getCleanComments());
        System.out.println("\n");
        System.out.println("Комментарии с заменой на звездочки: ");

        WordsToStars commentsWithStars = new WordsToStars();
        commentsWithStars.filterComments(comments, blackList);
        System.out.println(commentsWithStars.getCleanComments()); /*Никак не получается восстановить разделители при выводе комментариев со звездочками,
                                                                        можно просто через запятую выводить все, но именно восстановить изначальные не знаю как :(*/
    }

}
