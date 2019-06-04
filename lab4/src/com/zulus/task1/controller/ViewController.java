package com.zulus.task1.controller;

import com.zulus.task1.model.Model;
import com.zulus.task1.view.View;

public class ViewController {
    private View view;
    private Model model;

    public ViewController (View view, Model model) {
        this.view = view;
        this.model = model;

        view.getButton().addActionListener((actionEvent) ->
                model.setText(view.getTextField().getText()));
    }
}
