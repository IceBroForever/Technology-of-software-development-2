package com.pasha1ruh.client.main;

import com.pasha1ruh.client.entities.RemoteSquareEquationSolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SquareEquationSolverClient extends JFrame {
    public SquareEquationSolverClient() {
        setTitle("Square Equation Solver");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Input:"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JPanel equationPanel = new JPanel();
        equationPanel.setLayout(new BoxLayout(equationPanel, BoxLayout.X_AXIS));
        equationPanel.add(new JLabel("("));
        JTextField aInput = new JTextField();
        aInput.setPreferredSize(new Dimension(30, aInput.getPreferredSize().height));
        equationPanel.add(aInput);
        equationPanel.add(new JLabel(")*x^2+("));
        JTextField bInput = new JTextField();
        bInput.setPreferredSize(new Dimension(30, aInput.getPreferredSize().height));
        equationPanel.add(bInput);
        equationPanel.add(new JLabel(")*x+("));
        JTextField cInput = new JTextField();
        cInput.setPreferredSize(new Dimension(30, aInput.getPreferredSize().height));
        equationPanel.add(cInput);
        equationPanel.add(new JLabel(")=0"));
        inputPanel.add(equationPanel);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel inputStatusPanel = new JPanel();
        inputStatusPanel.setLayout(new BorderLayout());
        JLabel errorOutput = new JLabel(" ");
        errorOutput.setForeground(Color.RED);
        inputStatusPanel.add(errorOutput, BorderLayout.CENTER);
        JButton solveButton = new JButton("Solve");
        inputStatusPanel.add(solveButton, BorderLayout.EAST);
        inputPanel.add(inputStatusPanel);

        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Result:"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        outputPanel.setLayout(new BorderLayout());
        outputPanel.add(new JLabel("Roots: "), BorderLayout.WEST);
        JLabel resultOutput = new JLabel(" ");
        outputPanel.add(resultOutput, BorderLayout.CENTER);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(inputPanel);
        getContentPane().add(outputPanel);

        pack();
        setSize(480, getHeight());
        setResizable(false);

        ActionListener actionListener = e -> {
            errorOutput.setText(" ");
            resultOutput.setText("-");
            try {
                RemoteSquareEquationSolver squareEquation = new RemoteSquareEquationSolver(
                        Double.parseDouble(aInput.getText()),
                        Double.parseDouble(bInput.getText()),
                        Double.parseDouble(cInput.getText())
                );
                double[] result = squareEquation.solve();
                resultOutput.setText(Arrays.toString(result));
            }
            catch (NumberFormatException exception) {
                errorOutput.setText("Bad format of input");
            }
            catch (Exception exception) {
                errorOutput.setText(exception.getMessage());
            }
        };
        solveButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new SquareEquationSolverClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
