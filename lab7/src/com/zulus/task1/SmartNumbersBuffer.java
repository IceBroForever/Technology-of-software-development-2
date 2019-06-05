package com.zulus.task1;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SmartNumbersBuffer {
    private final List<Double> elements;

    public SmartNumbersBuffer() {
        this.elements = Collections.synchronizedList(new LinkedList<>());
    }

    public double getSumOfSquares() {
        double sum = 0;
        synchronized (elements) {
            for (double el : elements) {
                sum += Math.pow(el, 2);
            }
        }
        return sum;
    }

    public void append(double el) {
        synchronized (elements) {
            this.elements.add(el);
        }
    }

    public List<Double> getElements() {
        return Collections.unmodifiableList(this.elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.elements.toArray());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof SmartNumbersBuffer)) {
            return false;
        }
        SmartNumbersBuffer candidate = (SmartNumbersBuffer) obj;
        return this.elements.equals(candidate.elements);
    }
}
