package com.zulus.task1.fileProcess;

import java.io.FileOutputStream;

public class FileWrite implements FileProcess {
    private String path;
    private String textToWrite;
    private FileOutputStream outputStream;

    public FileWrite(String path, String textToWrite) {
        this.path = path;
        this.textToWrite = textToWrite;
    }

    @Override
    public void process() {
        try {
            outputStream = new FileOutputStream(path);
            outputStream.write(textToWrite.getBytes("UTF-8"));
            outputStream.close();
        } catch (Exception f) {
            f.printStackTrace();
        }
    }
}
