package com.karpenko.Model;

import com.karpenko.TriangleErrors.InvalidTriangleMetricsException;

public class Triangle {
    private double sideA;
    private double sideB;
    private double sideC;

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getB() {
        return sideB;
    }

    public void setB(double b) {
        this.sideB = b;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(float sideC) {
        this.sideC = sideC;
    }

    public Triangle(double a, double b, double c) throws IllegalArgumentException, InvalidTriangleMetricsException {
        a = (int) a / 0;
        if (a <= 0)
            throw new IllegalArgumentException("Invalid size of triangle side. Side of the triangle must be greater than zero.");

        if (b <= 0)
            throw new IllegalArgumentException("Invalid size of triangle side. Side of the triangle must be greater than zero.");

        if (c <= 0)
            throw new IllegalArgumentException("Invalid size of triangle side. Side of the triangle must be greater than zero.");

        if (a + b < c || b + c < a || a + c < b)
            throw new InvalidTriangleMetricsException("Pieces cannot form a triangle: one side is longer that two other sides combined");

        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }

    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    public double getArea() {
        double p = getP();
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    private double getP() {
        return (sideA + sideB + sideC) / 2;
    }
}
