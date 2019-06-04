package com.pasha1ruh.square_equation;

public class SquareEquationSolver extends SquareEquation {
    public SquareEquationSolver(double a, double b, double c) {
        super(a, b, c);
    }

    public double calculateDeterminant() {
        return Math.pow(b, 2) - 4 * a * c;
    }

    @Override
    public double[] solve() throws IllegalStateException {
        double determinant = calculateDeterminant();
        if(determinant < 0) {
            throw new IllegalStateException("Negative determinant");
        }
        if(determinant == 0) {
            return new double[] { -b / (2*a) };
        }
        double determinantRoot = Math.sqrt(determinant);
        return new double[] {
                (-b - determinantRoot) / (2*a),
                (-b + determinantRoot) / (2*a)
        };
    }
}
