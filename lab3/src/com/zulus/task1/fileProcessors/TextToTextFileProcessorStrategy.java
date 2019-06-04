package com.zulus.task1.fileProcessors;

import java.util.List;

public class TextToTextFileProcessorStrategy extends BaseFileProcessorStartegy {
    public TextToTextFileProcessorStrategy(String inputFile, String outFile) {
        super(inputFile, outFile);
    }

    @Override
    public void writeToInputFile(List<Integer> numbers) {
        this.writeToTxtFile(this.inputFile, numbers);
    }

    @Override
    public List<Integer> readFromInputFile() {
        return this.collectNumbersFromTxtFile(this.inputFile);
    }

    @Override
    public void writeToOutputFile(List<Integer> numbers) {
        this.writeToTxtFile(this.outFile, numbers);
    }

}
