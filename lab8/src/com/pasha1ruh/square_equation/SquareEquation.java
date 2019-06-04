package com.pasha1ruh.square_equation;

public abstract class SquareEquation {
    protected double a;
    protected double b;
    protected double c;

    public SquareEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public abstract double[] solve();

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareEquation)) return false;

        SquareEquation that = (SquareEquation) o;

        if (Double.compare(that.a, a) != 0) return false;
        if (Double.compare(that.b, b) != 0) return false;
        return Double.compare(that.c, c) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(a != 0) {
            builder.append(a);
            builder.append("*x^2");
        }
        if(b != 0) {
            if(b > 0) builder.append("+");
            builder.append(b);
            builder.append("*x");
        }
        if(c != 0) {
            if(c > 0) builder.append("+");
            builder.append(b);
        }
        if(a == 0 && b == 0 && c == 0) builder.append(0);
        builder.append("=0");
        return builder.toString();
    }
}
