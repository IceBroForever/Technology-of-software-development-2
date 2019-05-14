package com.zulus.task1;

import com.zulus.task1.controller.ViewController;
import com.zulus.task1.model.Model;
import com.zulus.task1.view.View;


public class Main {
    public static void main(String... args) {
        Model model = new Model();
        View view = new View("Hello", model);
        ViewController controller = new ViewController(view, model);
        view.setVisible(true);

    }
}
