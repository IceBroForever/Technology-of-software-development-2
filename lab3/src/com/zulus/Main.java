package com.zulus;

public class Main {
    public static void main(String[] args) {
        NumbersStore numbersStore = new NumbersStore(100);
        ConsoleView consoleView = new ConsoleView(numbersStore);
        Controller controller = new Controller(consoleView, numbersStore);
        controller.run();
    }
}
