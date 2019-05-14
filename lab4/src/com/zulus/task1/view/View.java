package com.zulus.task1.view;

import com.zulus.task1.model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
    private JButton button;
    private JTextField textField;
    private JTextArea textArea;
    private JMenuItem menuOpen;
    private JMenuItem menuSave;
    JTextField textToSave;
    private Model model;

    public View (String title, Model model) {
        super(title);
        this.model = model;
        this.model.addObserver(this);
        setSize( 400, 500 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        setLayout(new BorderLayout());       // set the layout manager
        textField = new JTextField("", 50);


        textToSave = new JTextField();
        Label label1 = new Label("");
        textArea = new JTextArea("");
        label1.setBackground(Color.WHITE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);

        JMenu myMenu = new JMenu("File");
        menuOpen = new JMenuItem("Open");
        menuOpen.addActionListener(new OpenFile(model.getText(), textArea));
        myMenu.add(menuOpen);

        menuSave = new JMenuItem("Write");
        menuSave.addActionListener(new WriteFile(textArea));

        myMenu.add(menuSave);
        menuBar.add(myMenu);

        JPanel north = new JPanel();
        north.setLayout(new GridLayout(3, 2));
        north.add(menuBar);
        north.add(label1);

        JLabel label = new JLabel("Enter text: ");
        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textFieldPanel.add(label, BorderLayout.WEST);
        textFieldPanel.add(textField, BorderLayout.CENTER);
        north.add(textFieldPanel, BorderLayout.NORTH);
        add(north, BorderLayout.PAGE_START);


        button = new JButton("Process text");

        textArea.setEditable(false);
        add(textArea);
        JPanel panel = new JPanel();
        panel.add(button);
        add(panel, BorderLayout.PAGE_END);


    }

    public JButton getButton() {
        return button;
    }


    public JTextField getTextField() {
        return textField;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (textArea != null) {
            textField.setText("");
            textArea.setText(model.getText().textProcess());
//            textToSave.setText(model.getText().getTextString());
        }
    }
}
