package com.gmail.pasha1ruh.views;

import com.gmail.pasha1ruh.models.smart_array.SmartArray;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ArrayWorkerCUI implements Observer {
    private SmartArray model;

    public ArrayWorkerCUI(SmartArray model) {
        if(model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        this.model = model;
        this.model.addObserver(this);
    }

    private void printCurrentState() {
        System.out.println("Current array: " + model);
        System.out.println("Sum of negative: " + model.filter((v, i) -> v < 0, 0, model.length())
                .reduce((r, v, i) -> r + v, 0d)
                .toString());

        System.out.print("Product between min and max: ");
        int indexOfMin = model.indexOfMin();
        int indexOfMax = model.indexOfMax();
        if (indexOfMin == -1 || indexOfMax == -1) {
            System.out.println("-");
            return;
        } else {
            System.out.println(indexOfMin < indexOfMax
                    ? model.reduce((r, v, i) -> r * v, 1d, indexOfMin, indexOfMax + 1)
                    : model.reduce((r, v, i) -> r * v, 1d, indexOfMax, indexOfMin + 1));
        }
        System.out.println("Write new array: ");
    }

    @Override
    public void update(Observable o, Object arg) {
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
