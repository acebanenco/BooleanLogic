package com.acebanenco.blogic.model;

public class Constant implements BooleanExpression {
    final int value;

    public Constant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
