package com.zulus.task2;

import com.zulus.task2.view.View;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        view.getSortButton().addActionListener((actionEvent) ->
                model.process());

        view.getGenerateButton().addActionListener((actionEvent) ->
                model.randomize());
    }
}
