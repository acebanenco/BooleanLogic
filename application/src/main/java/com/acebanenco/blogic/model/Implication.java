package com.acebanenco.blogic.model;

public class Implication implements BinaryOperatorExpression {
    final BooleanExpression lhs;
    final BooleanExpression rhs;

    public Implication(BooleanExpression lhs, BooleanExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public BooleanExpression getLhs() {
        return lhs;
    }

    @Override
    public BooleanExpression getRhs() {
        return rhs;
    }

    @Override
    public String toString() {
        return BooleanExpressionStringifier.stringify("=>", this);
    }
}
