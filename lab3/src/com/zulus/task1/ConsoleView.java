package com.zulus.task1;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ConsoleView implements Observer {
    private NumbersStore numbersStore;
    private Scanner scanner;

    public ConsoleView(NumbersStore numbersStore) {
        this.numbersStore = numbersStore;
        this.numbersStore.addObserver(this);
        this.scanner = new Scanner(System.in);
    }

    public int getInput() {
        System.out.println("Choose a method for processing numbers: \n" +
                "1. Text files\n" +
                "2. Binary files\n" +
                "3. One binary file\n" +
                "4. From text to binary\n" +
                "5. From binary to text\n");
        return scanner.nextInt();
    }

    public void printNotification(String notification) {
        System.out.println(notification);
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            System.out.println("Input: " + numbersStore.getNumbers());
            System.out.println("Result: " + numbersStore.getProcessedNumbers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}