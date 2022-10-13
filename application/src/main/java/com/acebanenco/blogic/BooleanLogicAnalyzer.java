package com.acebanenco.blogic;

import com.acebanenco.blogic.model.BooleanExpression;
import com.acebanenco.blogic.model.Identifier;
import com.acebanenco.blogic.tree.ExpressionTreeWalker;

import java.util.LinkedHashSet;
import java.util.Set;

public class BooleanLogicAnalyzer {
    public Set<String> getIdentifiers(BooleanExpression expression) {
        Set<String> identifiers = new LinkedHashSet<>();
        new ExpressionTreeWalker().walkTree(expression, x -> {
            if ( x instanceof Identifier) {
                identifiers.add(((Identifier) x).getToken());
            }
        });
        return identifiers;
    }
}
