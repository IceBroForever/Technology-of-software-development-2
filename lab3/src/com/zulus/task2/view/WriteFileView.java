package com.zulus.task2.view;

import com.zulus.task2.fileProcessors.FileWrite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WriteFileView implements ActionListener {
    JFileChooser chooser;
    String path;
    JTextArea inputStr;

    public WriteFileView(JTextArea inputStr) {
        this.chooser = new JFileChooser();
        this.inputStr = inputStr;
    }


    public void actionPerformed(ActionEvent e) {
        try {
            int code = chooser.showOpenDialog(null);
            if (code == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                path = selectedFile.getAbsolutePath();
                System.out.println(path);
                FileWrite fileWriter = new FileWrite(path, inputStr.getText());
                fileWriter.process();
            }
        } catch (Exception f) {
            f.printStackTrace();
        }
    }
}
