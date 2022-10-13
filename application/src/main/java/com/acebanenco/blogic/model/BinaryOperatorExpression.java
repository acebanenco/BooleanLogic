package com.acebanenco.blogic.model;

public interface BinaryOperatorExpression extends BooleanExpression {
    BooleanExpression getLhs();
    BooleanExpression getRhs();
}
