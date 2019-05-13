package com.gmail.pasha1ruh.main;

import com.gmail.pasha1ruh.models.smart_array.SmartArray;
import com.gmail.pasha1ruh.views.ArrayWorkerCUI;
import com.gmail.pasha1ruh.views.ArrayWorkerGUI;

public class Main {
    public static void main(String[] args) {
        SmartArray model = new SmartArray();

		javax.swing.SwingUtilities.invokeLater(() -> {
        	ArrayWorkerGUI view = new ArrayWorkerGUI(model);
            view.setDefaultCloseOperation(ArrayWorkerGUI.EXIT_ON_CLOSE);
            view.setVisible(true);
        });

        new ArrayWorkerCUI(model).start();
    }
}
