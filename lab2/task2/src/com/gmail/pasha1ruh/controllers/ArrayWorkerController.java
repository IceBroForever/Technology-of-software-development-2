package com.gmail.pasha1ruh.controllers;

import com.gmail.pasha1ruh.models.smart_array.SmartArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ArrayWorkerController implements ActionListener, Observer {
    private SmartArray array;

    private final JTextField arrayInput;
    private final JLabel errorOutput;
    private final JButton computeButton;
    private final JLabel sumOfNegativeOutput;
    private final JLabel productBetweenMinAndMaxOutput;

    public ArrayWorkerController(
            SmartArray array,
            JTextField arrayInput,
            JLabel errorOutput,
            JButton computeButton,
            JButton restoreButton,
            JLabel sumOfNegativeOutput,
            JLabel productBetweenMinAndMaxOutput
    ) {
        this.array = array;
        this.arrayInput = arrayInput;
        this.errorOutput = errorOutput;
        this.computeButton = computeButton;
        this.sumOfNegativeOutput = sumOfNegativeOutput;
        this.productBetweenMinAndMaxOutput = productBetweenMinAndMaxOutput;

        this.computeButton.addActionListener(this);
        restoreButton.addActionListener(this);

        this.array.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sumOfNegativeOutput.setText(" ");
        productBetweenMinAndMaxOutput.setText(" ");
        errorOutput.setText(" ");
        if (e.getSource() == computeButton) {
            try {
                array.setArrayFromString(arrayInput.getText());
            } catch (Exception error) {
                errorOutput.setText(error.getMessage());
            }
        } else {
            array.setArray(new double[]{});
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o != this.array) {
            return;
        }
        arrayInput.setText(this.array.toString());
        sumOfNegativeOutput.setText(
                array.filter((v, i) -> v < 0, 0, array.length())
                        .reduce((r, v, i) -> r + v, 0d)
                        .toString()
        );

        int indexOfMin = array.indexOfMin();
        int indexOfMax = array.indexOfMax();
        if (indexOfMin == -1 || indexOfMax == -1) {
            productBetweenMinAndMaxOutput.setText("-");
            return;
        }
        double result = indexOfMin < indexOfMax
                ? array.reduce((r, v, i) -> r * v, 1d, indexOfMin, indexOfMax + 1)
                : array.reduce((r, v, i) -> r * v, 1d, indexOfMax, indexOfMin + 1);

        productBetweenMinAndMaxOutput.setText(Double.toString(result));
    }
}
