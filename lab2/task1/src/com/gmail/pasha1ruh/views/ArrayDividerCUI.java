package com.gmail.pasha1ruh.views;

import com.gmail.pasha1ruh.models.SmartArray;
import com.gmail.pasha1ruh.observe.Observer;

import java.util.Scanner;

public class ArrayDividerCUI implements Observer {
    private SmartArray model;

    public ArrayDividerCUI(SmartArray model) {
        if(model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        this.model = model;
        this.model.addObserver(this);
    }

    private void printCurrentState() {
        System.out.println("Current array: " + model);
        System.out.println("Even numbers: " + model.getEvenNumbers());
        System.out.println("Odd numbers: " + model.getOddNumbers());
        System.out.println("Write new array: ");
    }

    @Override
    public void update() {
        printCurrentState();
    }

    public void start() {
        printCurrentState();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String result = scanner.nextLine();
            try {
                model.setArrayFromString(result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                printCurrentState();
            }
        }
    }
}
