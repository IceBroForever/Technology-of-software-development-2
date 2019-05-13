package com.zulus.fileProcessors;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract public class BaseFileProcessorStartegy {
    protected String inputFile;
    protected String outFile;

    public BaseFileProcessorStartegy(String inputFile, String outFile) {
        this.inputFile = inputFile;
        this.outFile = outFile;
    }

    protected List<Integer> collectNumbersFromTxtFile(String inputFile) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

            ArrayList<Integer> numbers = new ArrayList<>();
            while (in.ready()) {
                String line = in.readLine();
                numbers.add(Integer.parseInt(line));
            }
            in.close();
            return numbers;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    protected List<Integer> collectNumbersFromBinaryFile(String inputFile) {
        try {
            List<Integer> numbers = new ArrayList<>();
            DataInputStream in = new DataInputStream(new FileInputStream(inputFile));
            while (in.available() > 0) {
                numbers.add(in.readInt());
                if (in.readChar() != '\n')
                    break;
            }
            in.close();
            return numbers;
        } catch (IOException err) {
            err.printStackTrace();
            return new ArrayList<>();
        }
    }

    protected void writeToBinaryFile(String outputFileName, List<Integer> numbers) {
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFileName));
            for (int number : numbers) {
                outputStream.writeInt(number);
                outputStream.writeChar('\n');
            }
            outputStream.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    protected void writeToTxtFile(String outputFileName, List<Integer> numbers) {
        try {
            FileWriter fileWriter = new FileWriter(outputFileName);
            for (int number : numbers)
                fileWriter.write(number + "\n");
            fileWriter.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public abstract  void writeToInputFile(List<Integer> numbers);

    public abstract List<Integer> readFromInputFile();

    public abstract void writeToOutputFile(List<Integer> numbers);
}
