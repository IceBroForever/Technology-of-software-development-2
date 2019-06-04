package com.zulus.task1.text;


import com.zulus.task1.sentance.Sentence;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Text {
    private List<Sentence> text = new ArrayList<>();


    public Text() {
    }

    private List<Sentence> textToStringList(String text) {
        ArrayList<Sentence> stringList = new ArrayList<>();
        BreakIterator iterator = BreakIterator.getSentenceInstance();
        text = text.replaceAll("(\r\n|\r|\n)", "");
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
            stringList.add(new Sentence(text.substring(start, end)));
        }
        return stringList;
    }

    public String textProcess() {
        String result = "";
        for (Sentence sentence : text) {
            for (String word : sentence.processSentance()) {
                result += word + '\n';
            }
        }
        return result;
    }

    public String getTextString() {
        String textToTextArea = "";
        for (Sentence sen : text) {
            textToTextArea += sen.getSentence() + "\r\n";
        }
        return textToTextArea;
    }

    public void setTextFromString(String textString) {
        String[] strings = textString.split("\n");
        for (String str : strings) {
            text.add(new Sentence(str));
        }
    }


    public void add(Sentence sentence) {
        text.add(sentence);
    }

    public Sentence removeItemByIndex(int index) {
        return text.remove(index);
    }

    public void setItemByIndex(int index, Sentence sentence) {
        text.set(index, sentence);
    }

    public Sentence getItemByIndex(int index) {
        return text.get(index);
    }

    public List<Sentence> getText() {
        return new ArrayList<>(text);
    }

    public void setText(List<Sentence> text) {
        this.text = new ArrayList<>(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text1 = (Text) o;
        return Objects.equals(text, text1.text);
    }
}
