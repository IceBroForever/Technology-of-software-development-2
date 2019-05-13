package com.gmail.pasha1ruh.views;

import com.gmail.pasha1ruh.models.Notebook;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotebookView extends JFrame {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Notebook model;
    private Date date = null;

    private JTextField dateInput;
    private JLabel errorOutput;
    private JPanel tasksPanel;
    private JScrollPane uncompletedTasksScrollPane;
    private JPanel uncompletedTasksPanel;
    private JScrollPane completedTasksScrollPane;
    private JPanel completedTasksPanel;

    public NotebookView(Notebook model) {
        if(model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        this.model = model;

        dateFormat.setLenient(false);

        setTitle("Task 4");
        setSize(840, 480);
        setResizable(false);

        JPanel dateInputPanel = new JPanel();
        dateInputPanel.setLayout(new BorderLayout(5, 5));
        dateInputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        dateInputPanel.add(new JLabel("Date: "), BorderLayout.WEST);
        dateInput = new JTextField();
        dateInputPanel.add(dateInput, BorderLayout.CENTER);
        errorOutput = new JLabel(" ");
        errorOutput.setForeground(Color.RED);
        dateInputPanel.add(errorOutput, BorderLayout.SOUTH);

        dateInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) { }
        });

        tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.X_AXIS));
        tasksPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Goals"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JPanel uncompletedTasksFullPanel = new JPanel();
        uncompletedTasksFullPanel.setLayout(new BorderLayout(5, 5));

        uncompletedTasksPanel = new JPanel();
        uncompletedTasksScrollPane = new JScrollPane(uncompletedTasksPanel);
        uncompletedTasksScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        uncompletedTasksScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        uncompletedTasksPanel.setLayout(new BoxLayout(uncompletedTasksPanel, BoxLayout.Y_AXIS));
        uncompletedTasksFullPanel.add(uncompletedTasksScrollPane, BorderLayout.CENTER);

        JPanel taskInputPanel = new JPanel();
        taskInputPanel.setLayout(new BorderLayout(5, 5));
        JTextField taskInput = new JTextField();
        taskInputPanel.add(taskInput, BorderLayout.CENTER);
        JButton taskInputButton = new JButton("Add task");
        taskInputButton.addActionListener((e) -> {
            String note = taskInput.getText();
            if(!note.isEmpty()) {
                model.addTask(date, note);
                taskInput.setText("");
                updateTasks();
            }
        });
        taskInputPanel.add(taskInputButton, BorderLayout.EAST);
        uncompletedTasksFullPanel.add(taskInputPanel, BorderLayout.SOUTH);

        tasksPanel.add(uncompletedTasksFullPanel);
        tasksPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        JPanel completedTasksFullPanel = new JPanel();
        completedTasksFullPanel.setLayout(new BorderLayout(5, 5));

        completedTasksPanel = new JPanel();
        completedTasksScrollPane = new JScrollPane(completedTasksPanel);
        completedTasksScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        completedTasksScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        completedTasksPanel.setLayout(new BoxLayout(completedTasksPanel, BoxLayout.Y_AXIS));
        completedTasksFullPanel.add(completedTasksScrollPane, BorderLayout.CENTER);

        tasksPanel.add(completedTasksFullPanel);

        tasksPanel.setVisible(false);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(dateInputPanel, BorderLayout.NORTH);
        getContentPane().add(tasksPanel, BorderLayout.CENTER);
    }

    private void updateDate() {
        String dateString = dateInput.getText();

        date = null;
        errorOutput.setText(" ");
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException exception) {
            errorOutput.setText("Date " + dateString + " in not valid according " + ((SimpleDateFormat)dateFormat).toPattern() + " pattern");
        }
        updateTasks();
    }

    private void updateTasks() {
        if(date == null) {
            tasksPanel.setVisible(false);
            return;
        }
        tasksPanel.setVisible(true);
        updateCompletedTasks();
        updateUncompletedTasks();
    }

    private void updateCompletedTasks() {
        completedTasksPanel.removeAll();
        List<String> notes = model.getCompletedTasks(date);
        if(notes == null) {
            JPanel noCompletedTasksPanel = new JPanel();
            noCompletedTasksPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            noCompletedTasksPanel.setLayout(new BorderLayout(5, 5));
            noCompletedTasksPanel.add(new JLabel("No completed tasks"), BorderLayout.CENTER);
            noCompletedTasksPanel.setMaximumSize(new Dimension(completedTasksPanel.getWidth(), 30));
            completedTasksPanel.add(noCompletedTasksPanel);
        } else {
            for(String note: notes) {
                JPanel taskPanel = new JPanel();
                taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                taskPanel.setLayout(new BorderLayout(5, 5));
                taskPanel.add(new JLabel("<html>" + note + "</html>"), BorderLayout.CENTER);
                taskPanel.setMaximumSize(new Dimension(completedTasksPanel.getWidth(), 30));
                completedTasksPanel.add(taskPanel);
            }
        }
        completedTasksPanel.revalidate();
        completedTasksScrollPane.revalidate();
        completedTasksPanel.repaint();
        completedTasksScrollPane.repaint();
    }

    private void updateUncompletedTasks() {
        uncompletedTasksPanel.removeAll();
        List<String> notes = model.getUncompletedTasks(date);
        if(notes == null) {
            JPanel noTasksPanel = new JPanel();
            noTasksPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            noTasksPanel.setLayout(new BorderLayout(5, 5));
            noTasksPanel.add(new JLabel("No tasks"), BorderLayout.CENTER);
            noTasksPanel.setMaximumSize(new Dimension(uncompletedTasksPanel.getWidth(), 30));
            uncompletedTasksPanel.add(noTasksPanel);
        } else {
            for(int i = 0; i < notes.size(); i++) {
                final int index = i;
                JPanel taskPanel = new JPanel();
                taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                taskPanel.setLayout(new BorderLayout(5, 5));
                taskPanel.add(new JLabel("<html>" + notes.get(index) + "</html>"), BorderLayout.CENTER);
                JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
                JButton completeButton = new JButton("Complete");
                completeButton.addActionListener((e) -> {
                    model.markTaskAsCompleted(date, index);
                    updateTasks();
                });
                buttonsPanel.add(completeButton);
                buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener((e) -> {
                    model.removeTask(date, index);
                    updateTasks();
                });
                buttonsPanel.add(removeButton);
                taskPanel.add(buttonsPanel, BorderLayout.EAST);
                taskPanel.setMaximumSize(new Dimension(uncompletedTasksPanel.getWidth(), 30));
                uncompletedTasksPanel.add(taskPanel);
            }
        }
        uncompletedTasksPanel.revalidate();
        uncompletedTasksScrollPane.revalidate();
        uncompletedTasksPanel.repaint();
        uncompletedTasksScrollPane.repaint();
    }
}
