package com.karpenko.View;

import com.karpenko.Model.Triangle;
import com.karpenko.TriangleErrors.InvalidTriangleMetricsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriangleGUI extends JFrame {
    private static Triangle triangle;
    private JTextField side1TextField;
    private JTextField side2TextField;
    private JTextField side3TextField;
    private JButton actionButton;
    private JTextField outputTextField;

    private static final Integer width = 500;
    private static final Integer height = 250;

    public TriangleGUI(String title){
        super(title);
        constructUpperPanel();
        constructBottomPanel();
    }

    private void constructUpperPanel() {
        side1TextField = new JTextField(20);
        side2TextField = new JTextField(20);
        actionButton = new JButton("Calculate Triangle Area");
        side3TextField = new JTextField(20);
        JLabel label1 = new JLabel("Side a:");
        JLabel label2 = new JLabel("Side b:");
        JLabel label3 = new JLabel("Side c:");

        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
        inputPanel.add(label1);
        inputPanel.add(side1TextField);
        inputPanel.add(label2);
        inputPanel.add(side2TextField);
        inputPanel.add(label3);
        inputPanel.add(side3TextField);
        add(inputPanel, BorderLayout.NORTH);
    }

    private void constructBottomPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actionButton);
        add(buttonPanel, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new GridLayout(0, 1));
        JLabel labelResult = new JLabel("Result:");
        outputPanel.add(labelResult);
        outputTextField = new JTextField(20);
        outputPanel.add(outputTextField);
        add(outputPanel, BorderLayout.PAGE_END);
        actionButton.addActionListener(new ButtonListener());
    }

    public static void window(String windowName){
        JFrame jFrame = getFrame(windowName);
    }

    private static JFrame getFrame(String title){
        TriangleGUI jFrame = new TriangleGUI(title);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(
                dimension.width / 2 - TriangleGUI.width / 2,
                dimension.height / 2 - TriangleGUI.height / 2,
                TriangleGUI.width, TriangleGUI.height
        );
        jFrame.setVisible(true);
        return  jFrame;
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String aSideFieldText = side1TextField.getText();
            String bSideFieldText = side2TextField.getText();
            String cSideFieldText = side3TextField.getText();

            try {
                double aSide = Double.parseDouble(aSideFieldText);
                double bSide = Double.parseDouble(bSideFieldText);
                double cSide = Double.parseDouble(cSideFieldText);

                Triangle triangle = new Triangle(aSide, bSide,cSide);
                double area = triangle.getArea();
                outputTextField.setText(Double.toString(area));
            } catch (NumberFormatException numberFormatException){
                outputTextField.setText("");
                JOptionPane.showMessageDialog(new JFrame(), numberFormatException.getMessage(),
                        "Number Format Exception", JOptionPane.ERROR_MESSAGE);
            } catch (ArithmeticException arithmeticException){
                outputTextField.setText("");
                JOptionPane.showMessageDialog(new JFrame(), arithmeticException.getMessage(),
                        "Arithmetic Exception", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidTriangleMetricsException invalidTriangleMetricsException ){
                outputTextField.setText("");
                JOptionPane.showMessageDialog(new JFrame(), invalidTriangleMetricsException,
                        "Invalid Triangle Metrics Exception", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exception){
                outputTextField.setText("");
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage(),
                        "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}