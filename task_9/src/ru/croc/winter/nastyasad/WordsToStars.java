package ru.croc.winter.nastyasad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WordsToStars implements BlackListFilter {
    List<String> cleanComments = new ArrayList<>();

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (String comment : comments) {
            String[] newWords = comment.split("[.,:;?!-]|\\s");

            for (String word : newWords) {
                for (String badWord : blackList) {
                    if (word.equals(badWord)) {
                        char[] ourWord = word.toCharArray();
                        for (int i = 0; i < ourWord.length; i++) {
                            ourWord[i] = '*';
                        }
                        String oldWord = word;

                        word = new String(ourWord);
                        newWords[Arrays.asList(newWords).indexOf(oldWord)] = word;
                    }

                }

            }
            comment = String.join("", newWords);
            cleanComments.add(comment);
        }

    }

    public String getCleanComments() {
        return cleanComments.toString();
    }
}
