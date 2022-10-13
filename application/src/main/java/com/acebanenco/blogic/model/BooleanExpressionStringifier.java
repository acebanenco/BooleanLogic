package com.acebanenco.blogic.model;

public class BooleanExpressionStringifier {

    public static String stringify(String op, BinaryOperatorExpression exp) {
        return stringify(op, exp.getLhs(), exp.getRhs());
    }

    public static String stringify(String op, UnaryOperatorExpression exp) {
        return stringify(op, exp.getRhs());
    }

    private static String stringify(String op, BooleanExpression lhs, BooleanExpression rhs) {
        return stringify(lhs) + " " + op + " " + stringify(rhs);
    }

    private static String stringify(String op, BooleanExpression rhs) {
        return op + stringify(rhs);
    }

    private static String stringify(BooleanExpression lhs) {
        return lhs instanceof BinaryOperatorExpression ? "(" + lhs + ")" : lhs.toString();
    }
}
