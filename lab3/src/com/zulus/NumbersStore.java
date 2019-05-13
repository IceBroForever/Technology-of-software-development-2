package com.zulus;

import com.zulus.fileProcessors.BaseFileProcessorStartegy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class NumbersStore extends Observable {
    private List<Integer> numbers;
    private BaseFileProcessorStartegy strategy;

    public NumbersStore(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers cannot be null");
        }
        this.numbers = new ArrayList<>(numbers);
    }

    public NumbersStore(int len) {
        if (len <= 0) {
            throw new IllegalArgumentException("len must be greater, than 0");
        }
        this.numbers = new ArrayList<>(len);
        for (int i = 0; i < 100; i++) {
            numbers.add(0);
        }
    }


    public void fillRandom(int min, int max, int len) {
        int diff = max - min;
        for (int i = 0; i < len; i++) {
            this.numbers.set(i, (int) (min + Math.random() * diff));
        }
    }

    public void fillRandom(int min, int max) {
        this.fillRandom(min, max, this.numbers.size());
    }

    public void setStrategy(BaseFileProcessorStartegy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public BaseFileProcessorStartegy getStrategy() {
        return strategy;
    }

    protected List<Integer> modifyEache3thElements(List<Integer> numbers) {
        List<Integer> modifiedNumbers = new ArrayList<>(numbers);
        int firstNegative = this.getFirstNegative(numbers);
        int lastNegative = this.getLastNegative(numbers);
        for (int i = 2; i < numbers.size(); i += 3) {
            modifiedNumbers.set(i, numbers.get(i) * 2 * (firstNegative + lastNegative));
        }
        return modifiedNumbers;
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

    public List<Integer> getProcessedNumbers() {
        return this.modifyEache3thElements(this.numbers);
    }

    public void save() {
        this.strategy.writeToInputFile(this.numbers);
    }

    public void process() {
        if (this.strategy != null) {
            this.strategy.writeToInputFile(this.numbers);
            NumbersStore ns = new NumbersStore(this.strategy.readFromInputFile());
            this.strategy.writeToOutputFile(ns.getProcessedNumbers());
            this.setChanged();
            this.notifyObservers();
        }
    }
}
