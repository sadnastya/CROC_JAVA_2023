package ru.croc.winter.nastyasad;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class BlackListPredicates {
    public static Predicate<String>  simpleFilter(CharSequence[] blackList ) {

        return p -> {
            for (CharSequence word : blackList) {
                if (p.toLowerCase().contains(word)) {
                    return false;
                }
            }
            return true;
        };
    }
}
