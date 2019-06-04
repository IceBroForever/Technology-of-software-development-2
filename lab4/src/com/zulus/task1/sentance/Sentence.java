package com.zulus.task1.sentance;

import java.lang.reflect.Array;
import java.util.*;

public class Sentence {
    private String sentence;
    private static final char[] chars = {'a', 'e', 'i', 'o', 'u'};

    public Sentence() {
        sentence = "";
    }

    public Sentence(String sentence) {
        this.sentence = sentence;
    }


    public Set<String> processSentance() {
        Set<String> resultSet = new HashSet<String>();
        String[] words;
        words = sentence.split(" ");

        for (String word : words) {
            if (word.length() > 0 && Arrays.binarySearch(chars, word.charAt(0)) >= 0) {
                resultSet.add(word);
            }
        }
        return resultSet;
    }


    @Override
    public String toString() {
        return "Sentence='" + sentence + "\n";
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sentence sentence1 = (Sentence) obj;
        return Objects.equals(sentence, sentence1.sentence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentence);
    }
}
