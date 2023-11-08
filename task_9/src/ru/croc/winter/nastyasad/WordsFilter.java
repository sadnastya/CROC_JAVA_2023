package ru.croc.winter.nastyasad;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WordsFilter implements BlackListFilter {
    List<String> cleanComments = new ArrayList<>();

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (String comment : comments) {
            int flag = 0;
            String[] newWords = comment.split("[.,:;?!-]|\\s");

            for (String word : newWords) {
                for (String badWord : blackList) {
                    if (word.equals(badWord)) {
                        flag = 1;
                        break;
                    }

                }
            }
            if (flag == 0) {
                cleanComments.add(comment);
            }

        }
    }

    public String getCleanComments() {
        return cleanComments.toString();
    }
}
