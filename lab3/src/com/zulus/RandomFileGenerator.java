package com.zulus;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class RandomFileGenerator {
    private String filename;

    public RandomFileGenerator(String filename) {
        this.filename = filename;
    }

    public void write(int min, int max, int count) {
        int diff = Math.abs(max - min);
        File outFile = new File(this.filename);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)))) {
            for (int i = 0; i < count; i++) {
                out.println((int) (min + Math.random() * diff));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
