package com.karpenko.task1.main;

import com.karpenko.task1.numberProcess.NumberProcess;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        NumberProcess np = new NumberProcess(new int[5]);
        np.fillArrayWithRandomValues();

        ArrayList<Integer> randomList = np.getNumbers();
        System.out.println(randomList);

        ArrayList<Double> list = np.addSqrtOfProductOfMaxValueAndLastNum();
        System.out.println(list);
    }
}