package com.karpenko.task1.numberProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NumberProcess {
    private int[] numbers;
    private int maxRandom = 50;
    private int minRandom = -50;

    public NumberProcess(int arrayLength) {
        if (arrayLength <= 0)
            throw new IllegalArgumentException("Incorrect array length. Length must be greater then zero.");

        numbers = new int[arrayLength];
    }

    public NumberProcess(int[] numbers) {
        if (numbers == null)
            throw new IllegalArgumentException("Numbers must not be null.");

        this.numbers = new int[numbers.length];
        System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);
    }

    public void fillArrayWithRandomValues() {
        int[] number = new int[this.numbers.length];
        Random rand = new Random();
        for (int i = 0; i < this.numbers.length; i++) {
            number[i] = rand.nextInt(maxRandom - minRandom + 1) + minRandom;
        }
        System.arraycopy(number, 0, this.numbers, 0, number.length);
    }

    public double getElementByIndex(int index){
        if (index < 0)
            throw new IndexOutOfBoundsException("Incorrect index. Index must be greater then zero.");

        if (index >= this.numbers.length)
            throw new IndexOutOfBoundsException("Incorrect index. Index must be less than the array length.");

        return numbers[index];
    }

    public void setValueByIndex(int value, int index){
        if (index < 0)
            throw new IndexOutOfBoundsException("Incorrect index. Index must be greater then zero.");

        if (index >= this.numbers.length)
            throw new IndexOutOfBoundsException("Incorrect index. Index must be less than the array length.");

        numbers[index] = value;
    }

    public ArrayList<Integer> getNumbers() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int item: numbers) {
            list.add(item);
        }
        return list;
    }

    public int getArrayLength() {
        return this.numbers.length;
    }

    public int getMaxValue() {
        int maxValue = numbers[0];
        for (int i = 1; i < this.numbers.length; i++) {
            if (numbers[i] > maxValue)
                maxValue = numbers[i];
        }
        return maxValue;
    }

    public ArrayList<Double> addSqrtOfProductOfMaxValueAndLastNum() {
        ArrayList<Double> result = new ArrayList<>();
        int maxValue = getMaxValue();
        int productOfMaxAndLastValues = Math.abs(maxValue * numbers[this.numbers.length - 1]);
        double sqrtOfProduct = Math.sqrt(productOfMaxAndLastValues);
        for (int item: numbers) {
            result.add(item + sqrtOfProduct);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NumberProcess that = (NumberProcess) obj;
        return Arrays.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(numbers);
    }

    @Override
    public String toString() {
        return "NumberProcess{" +
                "numbers=" + Arrays.toString(numbers) +
                '}';
    }
}


