package com.zulus.task2;


import com.zulus.task2.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("Hello", model);
        Controller controller = new Controller(view, model);
        view.setVisible(true);
    }
}
