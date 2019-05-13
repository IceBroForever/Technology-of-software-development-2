package com.gmail.pasha1ruh.main;

import com.gmail.pasha1ruh.models.SmartArray;
import com.gmail.pasha1ruh.views.ArrayDividerCUI;
import com.gmail.pasha1ruh.views.ArrayDividerGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SmartArray model = new SmartArray();

		SwingUtilities.invokeLater(() -> {
            ArrayDividerGUI view = new ArrayDividerGUI(model);
            view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            view.setVisible(true);
        });

		new ArrayDividerCUI(model).start();
    }
}
