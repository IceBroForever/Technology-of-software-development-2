package com.zulus.task1.fileProcessors;

import java.util.List;

public class BinaryToTextFileProcessorStrategy extends BaseFileProcessorStartegy {
    public BinaryToTextFileProcessorStrategy(String inputFile, String outFile) {
        super(inputFile, outFile);
    }

    @Override
    public void writeToInputFile(List<Integer> numbers) {
        this.writeToBinaryFile(this.inputFile, numbers);
    }

    @Override
    public List<Integer> readFromInputFile() {
        return this.collectNumbersFromBinaryFile(this.inputFile);
    }

    @Override
    public void writeToOutputFile(List<Integer> numbers) {
        this.writeToTxtFile(this.outFile, numbers);
    }

}
