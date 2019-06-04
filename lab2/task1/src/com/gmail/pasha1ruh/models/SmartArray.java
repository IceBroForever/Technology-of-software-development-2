package com.gmail.pasha1ruh.models;

import com.gmail.pasha1ruh.observe.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartArray extends Observable {
    private int[] array;

    private static final Pattern pattern = Pattern.compile("(-?\\d+)(\\s*,\\s*(-?\\d+))*");

    public SmartArray() {
        this.array = new int[10];
    }

    public SmartArray(int[] array) {
        if(array == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        this.array = Arrays.copyOf(array, array.length);
    }

    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }

    public void setArray(int[] array) {
        if(array == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        this.array = Arrays.copyOf(array, array.length);
        updateObservers();
    }

    public void setArrayFromString(String string) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            array = Arrays.stream(string.split("\\s*,\\s*"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            updateObservers();
        } else {
            throw new IllegalArgumentException("String should look like this: integer [, integer]...");
        }
    }

    public int get(int index) {
        return array[index];
    }

    public void set(int index, int value) {
        array[index] = value;
        updateObservers();
    }

    public int length() {
        return array.length;
    }

    public SmartArray filter(Function<Integer, Boolean> criteria) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int v : array) {
            if(criteria.apply(v)) {
                list.add(v);
            }
        }

        return new SmartArray(
                list.stream()
                    .mapToInt(Integer::intValue)
                    .toArray()
        );
    }

    public SmartArray getEvenNumbers() {
        return filter((v) -> v % 2 == 0);
    }

    public SmartArray getOddNumbers() {
        return filter((v) -> v % 2 != 0);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            if (i != 0) builder.append(", ");
            builder.append(array[i]);
        }
        return builder.toString();
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
