package com.zulus.task2.view;

import com.zulus.task2.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
    private JButton sortButton;
    private JButton generateButton;
    private JTextArea textArea;
    private JMenuItem menuOpen;
    private JMenuItem menuSave;
    private Model model;

    public View(String title, Model model) {
        super(title);
        this.model = model;
        this.model.addObserver(this);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());       // set the layout manager
        // output text
        {
            textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            add(scrollPane,BorderLayout.CENTER);
        }
        // menu
        {
            JMenuBar menuBar = new JMenuBar();
            menuBar.setBorderPainted(false);

            JMenu myMenu = new JMenu("File");
            menuOpen = new JMenuItem("Open");
            menuOpen.addActionListener(new OpenFileView(model, textArea));
            myMenu.add(menuOpen);

            menuSave = new JMenuItem("Write");
            menuSave.addActionListener(new WriteFileView(textArea));

            myMenu.add(menuSave);
            menuBar.add(myMenu);
            add(menuBar, BorderLayout.NORTH);
        }

        // buttons
        {
            JPanel panel = new JPanel();

            sortButton = new JButton("Sort");
            panel.add(sortButton, BorderLayout.WEST);

            generateButton = new JButton("randomize");
            panel.add(generateButton, BorderLayout.EAST);

            add(panel, BorderLayout.PAGE_END);
        }

    }

    public JButton getSortButton() {
        return sortButton;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (textArea != null) {
            textArea.setText(model.getText());
        }
    }
}
