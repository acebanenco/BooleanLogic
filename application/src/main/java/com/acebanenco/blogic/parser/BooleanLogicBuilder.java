package com.acebanenco.blogic.parser;

import com.acebanenco.blogic.model.*;

import java.util.Deque;
import java.util.LinkedList;

public class BooleanLogicBuilder extends com.acebanenco.blogic.parser.BooleanExpressionBaseListener {
    private final Deque<BooleanExpression> stack = new LinkedList<>();

    public BooleanExpression getResult() {
        return stack.getFirst();
    }

    @Override
    public void exitNegation(com.acebanenco.blogic.parser.BooleanExpressionParser.NegationContext ctx) {
        BooleanExpression last = stack.removeFirst();
        stack.addFirst(new Negation(last));
    }

    @Override
    public void exitIdExpression(com.acebanenco.blogic.parser.BooleanExpressionParser.IdExpressionContext ctx) {
        stack.addFirst(new Identifier(ctx.id.getText()));
    }

    @Override
    public void exitConjunction(com.acebanenco.blogic.parser.BooleanExpressionParser.ConjunctionContext ctx) {
        BooleanExpression rhs = stack.removeFirst();
        BooleanExpression lhs = stack.removeFirst();
        stack.addFirst(new Conjunction(lhs, rhs));
    }

    @Override
    public void exitAlternative(com.acebanenco.blogic.parser.BooleanExpressionParser.AlternativeContext ctx) {
        BooleanExpression rhs = stack.removeFirst();
        BooleanExpression lhs = stack.removeFirst();
        stack.addFirst(new Alternative(lhs, rhs));
    }

    @Override
    public void exitImplicationOrEquality(com.acebanenco.blogic.parser.BooleanExpressionParser.ImplicationOrEqualityContext ctx) {
        BooleanExpression rhs = stack.removeFirst();
        BooleanExpression lhs = stack.removeFirst();
        stack.addFirst(ctx.eqOp != null ? new Equality(lhs, rhs) :
                ctx.implOp != null ? new Implication(lhs, rhs) : null);
    }

}
