package com.gmail.pasha1ruh.observe;

import java.util.LinkedList;
import java.util.List;

public class Observable {
    private List<Observer> observers = new LinkedList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public boolean removeObserver(Observer observer) {
        System.out.println("here");
        return observers.remove(observer);
    }

    protected void updateObservers() {
        for(Observer observer: observers) {
            observer.update();
        }
    }
}
