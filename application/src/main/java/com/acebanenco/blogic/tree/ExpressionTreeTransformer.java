package com.acebanenco.blogic.tree;

import com.acebanenco.blogic.model.*;

public class ExpressionTreeTransformer {

    public BooleanExpression transformTree(BooleanExpression expression, ExpressionMapper expressionMapper) {
        if (expression instanceof Alternative alternative) {
            BooleanExpression lhs = transformTree(alternative.getLhs(), expressionMapper);
            BooleanExpression rhs = transformTree(alternative.getRhs(), expressionMapper);
            return lhs == alternative.getLhs() && rhs == alternative.getRhs()
                    ? expressionMapper.map(alternative)
                    : expressionMapper.map(new Alternative(lhs, rhs));
        }
        if (expression instanceof Conjunction conjunction) {
            BooleanExpression lhs = transformTree(conjunction.getLhs(), expressionMapper);
            BooleanExpression rhs = transformTree(conjunction.getRhs(), expressionMapper);
            return lhs == conjunction.getLhs() && rhs == conjunction.getRhs()
                    ? expressionMapper.map(conjunction)
                    : expressionMapper.map(new Conjunction(lhs, rhs));
        }
        if (expression instanceof Equality equality) {
            BooleanExpression lhs = transformTree(equality.getLhs(), expressionMapper);
            BooleanExpression rhs = transformTree(equality.getRhs(), expressionMapper);
            return lhs == equality.getLhs() && rhs == equality.getRhs()
                    ? expressionMapper.map(equality)
                    : expressionMapper.map(new Equality(lhs, rhs));
        }
        if (expression instanceof Implication implication) {
            BooleanExpression lhs = transformTree(implication.getLhs(), expressionMapper);
            BooleanExpression rhs = transformTree(implication.getRhs(), expressionMapper);
            return lhs == implication.getLhs() && rhs == implication.getRhs()
                    ? expressionMapper.map(implication)
                    : expressionMapper.map(new Implication(lhs, rhs));
        }
        if ( expression instanceof Negation negation ) {
            BooleanExpression rhs = transformTree(negation.getRhs(), expressionMapper);
            return rhs == negation.getRhs()
                    ? expressionMapper.map(negation)
                    : expressionMapper.map(new Negation(rhs));
        }
        return expressionMapper.map(expression);
    }
}
