package com.zulus.task2.fileProcessors;
import java.io.*;

public class FileRead implements FileProcessor {
    private BufferedReader reader;
    private String path;
    private String data;

    public FileRead(String path) {
        this.path = path;
    }

    @Override
    public void process() {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String str = reader.readLine();
            while (str != null) {
                sb.append(str);
                sb.append(System.lineSeparator());
                str = reader.readLine();
            }
            data = sb.toString();
            reader.close();
        } catch (Exception f) {
            f.printStackTrace();
        }
    }

    public String getData() {
        return data;
    }
}
