package com.zulus.task1.view;

import com.zulus.task1.fileProcess.FileRead;
import com.zulus.task1.text.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFile implements ActionListener {
    JFileChooser chooser;
    String fileName;
    Text text;
    JTextArea textArea;

    public OpenFile(Text text, JTextArea textArea) {
        this.chooser = new JFileChooser();
        this.text = text;
        this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int code = chooser.showOpenDialog(null);
            if (code == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                FileRead fileReader = new FileRead(selectedFile.getAbsolutePath());
                fileReader.process();
                text.setTextFromString(fileReader.getData());
                textArea.setText(text.textProcess());
            }
        } catch(Exception f) {
            f.printStackTrace();
        }
    }
}
