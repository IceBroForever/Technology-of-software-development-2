package com.karpenko.task2.card;

import java.util.Objects;

public class ForeignWordCard {
    private String foreignWord;
    private String translatedWord;

    public ForeignWordCard(String foreignWord, String translatedWord) {
        this.foreignWord = foreignWord;
        this.translatedWord = translatedWord;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    @Override
    public String toString() {
        return "ForeignWordCard { " + foreignWord  + ": " + translatedWord + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForeignWordCard)) return false;
        ForeignWordCard that = (ForeignWordCard) o;
        return foreignWord.equals(that.foreignWord) &&
                translatedWord.equals(that.translatedWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foreignWord, translatedWord);
    }
}
