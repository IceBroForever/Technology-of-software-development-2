package com.karpenko.task2.collections;

import com.karpenko.task2.card.ForeignWordCard;

import java.util.HashMap;

public class ForeignLanguageDictionary {
    private HashMap<String, ForeignWordCard> cards = new HashMap<>();

    public ForeignLanguageDictionary() {}

    public ForeignLanguageDictionary(HashMap<String, ForeignWordCard> cards) {
        if (cards == null)
            throw new IllegalArgumentException("Invalid cards. Cards must not be null.");

        this.cards = new HashMap<>(cards);
    }

    public ForeignWordCard add(ForeignWordCard card) {
        if (card == null)
            throw new IllegalArgumentException("Invalid card. Card must not be null.");

        return cards.put(card.getForeignWord(), card);
    }

    public ForeignWordCard remove(String foreignWord) {
        if (foreignWord == null)
            throw new IllegalArgumentException("Invalid foreignWord. ForeignWord must not be null.");

        return cards.remove(foreignWord);
    }

    public ForeignWordCard get(String foreignWord) {
        if (foreignWord == null)
            throw new IllegalArgumentException("Invalid foreignWord. ForeignWord must not be null.");

        return cards.getOrDefault(foreignWord, null);
    }

    public ForeignLanguageDictionary union(ForeignLanguageDictionary dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException("Invalid dictionary. Dictionary must not be null.");

        cards.putAll(dictionary.cards);

        return new ForeignLanguageDictionary(cards);
    }

    public ForeignLanguageDictionary intersection(ForeignLanguageDictionary dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException("Invalid dictionary. Dictionary must not be null.");

        ForeignLanguageDictionary languageDictionary = new ForeignLanguageDictionary(cards);
        languageDictionary.cards.keySet().retainAll(dictionary.cards.keySet());

        return languageDictionary;
    }

    public ForeignLanguageDictionary difference(ForeignLanguageDictionary dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException("Invalid dictionary. Dictionary must not be null.");

        ForeignLanguageDictionary languageDictionary = new ForeignLanguageDictionary(cards);
        languageDictionary.cards.entrySet().removeAll(dictionary.cards.entrySet());
        return languageDictionary;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ForeignWordCard card: cards.values()){
            stringBuilder.append(card.toString());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }
}
