package com.gmail.pasha1ruh.main;

import com.gmail.pasha1ruh.views.ArrayWorkerView;

public class Main {
    public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
        	ArrayWorkerView view = new ArrayWorkerView();
            view.setDefaultCloseOperation(ArrayWorkerView.EXIT_ON_CLOSE);
            view.setVisible(true);
        });
    }
}
