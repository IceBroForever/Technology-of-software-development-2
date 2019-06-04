package com.zulus.task1.model;

import com.zulus.task1.sentance.Sentence;
import com.zulus.task1.text.Text;

import java.util.Observable;

public class Model extends Observable {
    private Text text;
    public Model(){
        text = new Text();
    }

    public void setText(String sentance) {
        Sentence sentence = new Sentence(sentance);
        text.add(sentence);
        text.textProcess();
        setChanged();
        notifyObservers();
    }

    public Text getText() {
        return text;
    }

}
