package ru.croc.winter.nastyasad;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public interface BlackListFilter {
    void filterComments(List<String> comments, Set<String> blackList);
}
