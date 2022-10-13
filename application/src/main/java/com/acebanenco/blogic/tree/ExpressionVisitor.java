package com.acebanenco.blogic.tree;

import com.acebanenco.blogic.model.BooleanExpression;

public interface ExpressionVisitor {
    void visitExpression(BooleanExpression expression);
}
