package com.gmail.pasha1ruh.models.smart_array;

@FunctionalInterface
public interface ThreeArgumentsFunction<FirstArgument, SecondArgument, ThirdArgument, Result> {
    public Result apply(FirstArgument firstArgument, SecondArgument secondArgument, ThirdArgument thirdArgument);
}
