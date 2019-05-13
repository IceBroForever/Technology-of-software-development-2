package com.gmail.pasha1ruh.views;

import com.gmail.pasha1ruh.controllers.ArrayWorkerController;
import com.gmail.pasha1ruh.models.smart_array.SmartArray;

import javax.swing.*;
import java.awt.*;

public class ArrayWorkerGUI extends JFrame {
    public ArrayWorkerGUI(SmartArray model) {
        setTitle("Task 1");
        setBounds(100, 100, 600, 240);
        setResizable(false);

        build(model);
    }

    private void build(SmartArray model) {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        addItem(new JLabel("Input"), 0, 0, 1, 1);

        JTextField arrayInput = new JTextField();
        addItem(arrayInput, 1, 0, 2, 1);

        JButton computeButton = new JButton("Compute");
        addItem(computeButton, 1, 1, 1, 1);

        JButton restoreButton = new JButton("Restore");
        addItem(restoreButton, 2, 1, 1, 1);

        JLabel errorOutput = new JLabel(" ");
        errorOutput.setForeground(Color.RED);
        addItem(errorOutput, 0, 2, 3, 1);

        addItem(new JLabel("Sum of negative"), 0, 3, 1, 1);

        JLabel sumOfNegativeNumbersOutput = new JLabel(" ");
        addItem(sumOfNegativeNumbersOutput, 1, 3, 2, 1);

        addItem(new JLabel("Product between min and max"), 0, 4, 1, 1);

        JLabel productBetweenMinAndMaxOutput = new JLabel(" ");
        addItem(productBetweenMinAndMaxOutput, 1, 4, 2, 1);

        new ArrayWorkerController(
                model,
                arrayInput,
                errorOutput,
                computeButton,
                restoreButton,
                sumOfNegativeNumbersOutput,
                productBetweenMinAndMaxOutput
        );
    }

    private void addItem(JComponent c, int x, int y, int width, int height) {
        GridBagConstraints gbc = new GridBagConstraints(
                x, y, width, height,1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5),
                5, 5);
        add(c, gbc);
    }
}
