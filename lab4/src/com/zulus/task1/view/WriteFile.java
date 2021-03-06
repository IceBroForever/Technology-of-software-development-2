package com.zulus.task1.view;


import com.zulus.task1.fileProcess.FileWrite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WriteFile implements ActionListener {
    JFileChooser chooser;
    String path;
    JTextArea inputStr;

    public WriteFile(JTextArea inputStr) {
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
        } catch(Exception f) {
            f.printStackTrace();
        }
    }
}
