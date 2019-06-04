package com.zulus.task1.fileProcessors;

import java.util.List;

public class BinaryToTheSameFileProcessorStrategy extends BaseFileProcessorStartegy {
    public BinaryToTheSameFileProcessorStrategy(String filename) {
        super(filename, filename);
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
        this.writeToBinaryFile(this.outFile, numbers);
    }

}
