package com.gmail.pasha1ruh.models.smart_array;

public interface TwoArgumentsFunction <FirstArgument, SecondArgument, Result> {
    public Result apply(FirstArgument firstArgument, SecondArgument secondArgument);
}
