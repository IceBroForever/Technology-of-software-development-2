package com.gmail.pasha1ruh.view;

import com.gmail.pasha1ruh.models.SmartArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrayDivider extends JFrame {
    private SmartArray model;

    private final JTextField arrayInput = new JTextField();
    private final JLabel errorOutput = new JLabel(" ");
    private final JButton divideButton = new JButton("Divide");
    private final JButton restoreButton = new JButton("Restore");
    private final JLabel evenNumbersOutput = new JLabel(" ");
    private final JLabel oddNumbersOutput = new JLabel(" ");

    public ArrayDivider(SmartArray model) {
        if(model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        this.model = model;
        build();
    }

    public class Controller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            evenNumbersOutput.setText(" ");
            oddNumbersOutput.setText(" ");
            errorOutput.setText(" ");
            if (e.getSource() == divideButton) {
                try {
                    model.setArrayFromString(arrayInput.getText());
                    evenNumbersOutput.setText(model.getEvenNumbers().toString());
                    oddNumbersOutput.setText(model.getOddNumbers().toString());
                } catch (Exception error) {
                    errorOutput.setText(error.getMessage());
                }
            } else {
                arrayInput.setText("");
            }
        }
    }

    private void build() {
        setTitle("Task 1");
        setBounds(100, 100, 400, 240);
        setResizable(false);

        errorOutput.setForeground(Color.RED);

        divideButton.addActionListener(new Controller());
        restoreButton.addActionListener(new Controller());

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        addItem(new JLabel("Input"), 0, 0, 1, 1);
        addItem(arrayInput, 1, 0, 2, 1);
        addItem(divideButton, 1, 1, 1, 1);
        addItem(restoreButton, 2, 1, 1, 1);
        addItem(errorOutput, 0, 2, 3, 1);
        addItem(new JLabel("Even numbers"), 0, 3, 1, 1);
        addItem(evenNumbersOutput, 1, 3, 2, 1);
        addItem(new JLabel("Even numbers"), 0, 4, 1, 1);
        addItem(oddNumbersOutput, 1, 4, 2, 1);
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
