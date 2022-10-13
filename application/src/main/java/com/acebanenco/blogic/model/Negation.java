package com.acebanenco.blogic.model;

public class Negation implements UnaryOperatorExpression {
    final BooleanExpression rhs;

    public Negation(BooleanExpression rhs) {
        this.rhs = rhs;
    }

    @Override
    public BooleanExpression getRhs() {
        return rhs;
    }

    @Override
    public String toString() {
        return BooleanExpressionStringifier.stringify("!", this);
    }
}
