package com.acebanenco.blogic.tree;

import com.acebanenco.blogic.model.BinaryOperatorExpression;
import com.acebanenco.blogic.model.BooleanExpression;
import com.acebanenco.blogic.model.UnaryOperatorExpression;

public class ExpressionTreeWalker {

    public void walkTree(BooleanExpression expression, ExpressionVisitor visitor) {
        visitor.visitExpression(expression);
        if ( expression instanceof UnaryOperatorExpression) {
            walkTree(((UnaryOperatorExpression) expression).getRhs(), visitor);
        } else if ( expression instanceof BinaryOperatorExpression) {
            walkTree(((BinaryOperatorExpression) expression).getLhs(), visitor);
            walkTree(((BinaryOperatorExpression) expression).getRhs(), visitor);
        }
    }
}
