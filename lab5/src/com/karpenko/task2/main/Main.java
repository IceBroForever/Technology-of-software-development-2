package com.karpenko.task2.main;

import com.karpenko.task2.card.ForeignWordCard;
import com.karpenko.task2.collections.ForeignLanguageDictionary;

public class Main {

    public static void main(String[] args) {
        ForeignWordCard card1 = new ForeignWordCard("Cat", "Кот");
        ForeignWordCard card2 = new ForeignWordCard("Dog", "Собака");
        ForeignWordCard card3 = new ForeignWordCard("Frog", "Лягушка");

        ForeignLanguageDictionary dictionary1 = new ForeignLanguageDictionary() {{
                add(card1);
                add(card2);
                add(card3);
        }};

        ForeignLanguageDictionary dictionary2 = new ForeignLanguageDictionary() {{
            add(card1);
        }};

        System.out.println(dictionary1.intersection(dictionary2));
        System.out.println(dictionary1.union(dictionary2));
        System.out.println(dictionary1.difference(dictionary2));
    }
}