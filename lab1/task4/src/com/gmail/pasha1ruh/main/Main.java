package com.gmail.pasha1ruh.main;

import com.gmail.pasha1ruh.models.Notebook;
import com.gmail.pasha1ruh.views.NotebookView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Notebook model = new Notebook();
            NotebookView view = new NotebookView(model);
            view.setDefaultCloseOperation(NotebookView.EXIT_ON_CLOSE);
            view.setVisible(true);
        });
    }
}
