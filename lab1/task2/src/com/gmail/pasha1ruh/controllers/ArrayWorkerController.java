package com.gmail.pasha1ruh.controllers;

import com.gmail.pasha1ruh.models.smart_array.SmartArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrayWorkerController implements ActionListener {
    private SmartArray array = new SmartArray();

    private final JTextField arrayInput;
    private final JLabel errorOutput;
    private final JButton computeButton;
    private final JLabel sumOfNegativeOutput;
    private final JLabel productBetweenMinAndMaxOutput;

    public ArrayWorkerController(
            JTextField arrayInput,
            JLabel errorOutput,
            JButton computeButton,
            JButton restoreButton,
            JLabel sumOfNegativeOutput,
            JLabel productBetweenMinAndMaxOutput
    ) {
        this.arrayInput = arrayInput;
        this.errorOutput = errorOutput;
        this.computeButton = computeButton;
        this.sumOfNegativeOutput = sumOfNegativeOutput;
        this.productBetweenMinAndMaxOutput = productBetweenMinAndMaxOutput;

        computeButton.addActionListener(this);
        restoreButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sumOfNegativeOutput.setText(" ");
        productBetweenMinAndMaxOutput.setText(" ");
        errorOutput.setText(" ");
        if (e.getSource() == computeButton) {
            try {
                array.setArrayFromString(arrayInput.getText());

                sumOfNegativeOutput.setText(
                        array.filter((v, i) -> v < 0, 0, array.length())
                            .reduce((r, v, i) -> r + v, 0d)
                            .toString()
                );

                int indexOfMin = array.indexOfMin();
                int indexOfMax = array.indexOfMax();
                double result = indexOfMin < indexOfMax
                        ? array.reduce((r, v, i) -> r * v, 1d, indexOfMin, indexOfMax + 1)
                        : array.reduce((r, v, i) -> r * v, 1d, indexOfMax, indexOfMin + 1);

                productBetweenMinAndMaxOutput.setText(Double.toString(result));
            } catch (Exception error) {
                errorOutput.setText(error.getMessage());
            }
        } else {
            arrayInput.setText("");
        }
    }
}
