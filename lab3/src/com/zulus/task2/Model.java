package com.zulus.task2;

import java.util.Observable;

public class Model extends Observable {
    private NumbersStore numbers;

    public Model() {
        numbers = new NumbersStore();
    }

    public void setText(String text) {
        this.numbers.load(text);
        setChanged();
        notifyObservers();
    }

    public String getText() {
        return numbers.toString();
    }

    public void randomize() {
        this.numbers.fillRandom(-100, 100, 100);
        setChanged();
        notifyObservers();
    }

    public void process() {
        this.numbers.sort();
        setChanged();
        notifyObservers();
    }
}
