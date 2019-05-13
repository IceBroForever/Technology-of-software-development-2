package com.gmail.pasha1ruh.main;

import com.gmail.pasha1ruh.models.SmartArray;
import com.gmail.pasha1ruh.view.ArrayDivider;

public class Main {
    public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
        	SmartArray model = new SmartArray();
        	ArrayDivider view = new ArrayDivider(model);
            view.setDefaultCloseOperation(ArrayDivider.EXIT_ON_CLOSE);
            view.setVisible(true);
        });
    }
}
