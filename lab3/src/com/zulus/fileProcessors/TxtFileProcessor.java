package com.zulus.fileProcessors;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFileProcessor {
    protected String inputFile;
    protected String outFile;

    public TxtFileProcessor(String inputFile, String outFile) {
        this.inputFile = inputFile;
        this.outFile = outFile;
    }

    protected int getFirstNegative(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number.compareTo(0) < 0) {
                return number;
            }
        }
        return 0;
    }

    protected int getLastNegative(List<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i).compareTo(0) < 0) {
                return numbers.get(i);
            }
        }
        return 0;
    }

    protected List<Integer> collectNumbersFromFile(BufferedReader in) throws IOException {
        ArrayList<Integer> numbers = new ArrayList<>();
        while (in.ready()) {
            String line = in.readLine();
            numbers.add(Integer.parseInt(line));
        }
        return numbers;
    }

    protected void modifyEache3thElements(List<Integer> numbers) {
        int firstNegative = this.getFirstNegative(numbers);
        int lastNegative = this.getLastNegative(numbers);
        for (int i = 2; i < numbers.size(); i += 3) {
            numbers.set(i, numbers.get(i) * 2 * (firstNegative + lastNegative));
        }
    }

    public void convertFile() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(this.inputFile)));
            File outFile = new File(this.outFile);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
            List<Integer> numbers = this.collectNumbersFromFile(in);
            this.modifyEache3thElements(numbers);
            for (Integer number : numbers) {
                out.println(number);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
