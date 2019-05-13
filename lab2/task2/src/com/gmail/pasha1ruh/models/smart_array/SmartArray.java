package com.gmail.pasha1ruh.models.smart_array;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartArray extends Observable {
    private double[] array;

    private static final Pattern pattern = Pattern.compile("(-?\\d+(.\\d+)?)(\\s*,\\s*(-?\\d+(.\\d+)?))*");

    public SmartArray() {
        this.array = new double[0];
    }

    public SmartArray(double[] array) {
        if(array == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        this.array = Arrays.copyOf(array, array.length);
    }

    public double[] getArray() {
        return Arrays.copyOf(array, array.length);
    }

    public void setArray(double[] array) {
        if(array == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        this.array = Arrays.copyOf(array, array.length);
        setChanged();
        notifyObservers();
    }

    public void setArrayFromString(String string) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            array = Arrays.stream(string.split("\\s*,\\s*"))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            setChanged();
            notifyObservers();
        } else {
            throw new IllegalArgumentException("String should look like this: double [, double]...");
        }
    }

    public double get(int index) {
        return array[index];
    }

    public void set(int index, double value) {
        array[index] = value;
        setChanged();
        notifyObservers();
    }

    public int length() {
        return array.length;
    }

    public SmartArray filter(TwoArgumentsFunction<Double, Integer, Boolean> criteria, int start, int end) {
        ArrayList<Double> list = new ArrayList<>();

        for(int i = start; i < end; i++) {
            if(criteria.apply(array[i], i)) {
                list.add(array[i]);
            }
        }

        return new SmartArray(
                list.stream()
                    .mapToDouble(Double::doubleValue)
                    .toArray()
        );
    }

    public <T> T reduce(ThreeArgumentsFunction<T, Double, Integer, T> agregator, T initial, int start, int end) {
        T result = initial;
        for(int i = start; i < end; i++) {
            result = agregator.apply(result, array[i], i);
        }
        return result;
    }

    public <T> T reduce(ThreeArgumentsFunction<T, Double, Integer, T> agregator, T initial) {
        return reduce(agregator, initial, 0, array.length);
    }

    public int indexOf(Function<Double, Boolean> criteria, int start, int end) {
        for(int i = start; i < end; i++) {
            if(criteria.apply(array[i])) return i;
        }
        return -1;
    }

    public int indexOf(Function<Double, Boolean> criteria) {
        return indexOf(criteria, 0, array.length);
    }

    public int indexOfMin() {
        if(array.length == 0) return -1;
        return reduce((iMin, curr, i) -> curr < array[iMin] ? i : iMin, 0, 1, array.length);
    }

    public int indexOfMax() {
        if(array.length == 0) return -1;
        return reduce((iMax, curr, i) -> curr > array[iMax] ? i : iMax, 0, 1, array.length);
    }

    public SmartArray getEvenNumbers() {
        return filter((v, i) -> v % 2 == 0, 0, array.length);
    }

    public SmartArray getOddNumbers() {
        return filter((v, i) -> v % 2 != 0, 0, array.length);
    }

    public void sort() {
        Arrays.sort(array);
        notifyObservers();
    }

    @Override
    public String toString() {
        return reduce(
                (str, v, i) -> str.append(i == 0 ? "" : ", ").append(v),
                new StringBuilder(),
                0,
                array.length
        ).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Arrays.equals(array, ((SmartArray)obj).array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
