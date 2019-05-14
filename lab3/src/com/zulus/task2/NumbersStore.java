package com.zulus.task2;

import java.util.*;

public class NumbersStore {
    private List<Integer> numbers;

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

    public NumbersStore() {
        this.numbers = new ArrayList<>();
    }

    public void fillRandom(int min, int max, int len) {
        this.numbers = new ArrayList<>(len);
        int diff = max - min;
        for (int i = 0; i < len; i++) {
            this.numbers.add((int) (min + Math.random() * diff));
        }
    }

    public void fillRandom(int min, int max) {
        this.fillRandom(min, max, this.numbers.size());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void load(String text) {
        String[] lines = text.split("\n");
        this.numbers = new ArrayList<>(lines.length);
        for (String line : lines) {
            this.numbers.add(Integer.parseInt(line));
        }
    }

    public void sort() {
        this.numbers.sort(null);
    }

    public void process() {
        this.sort();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer num : this.numbers) {
            stringBuilder.append(num);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
