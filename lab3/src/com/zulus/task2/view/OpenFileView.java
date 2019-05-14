package com.zulus.task2.view;

import com.zulus.task2.Model;
import com.zulus.task2.NumbersStore;
import com.zulus.task2.fileProcessors.FileRead;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFileView implements ActionListener {
    JFileChooser chooser;
    Model model;
    JTextArea textArea;

    public OpenFileView(Model model, JTextArea textArea) {
        this.chooser = new JFileChooser();
        this.model = model;
        this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int code = chooser.showOpenDialog(null);
            if (code == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                FileRead fileReader = new FileRead(selectedFile.getAbsolutePath());
                fileReader.process();
                model.setText(fileReader.getData());
                textArea.setText(model.getText());
            }
        } catch (Exception f) {
            f.printStackTrace();
        }
    }
}
