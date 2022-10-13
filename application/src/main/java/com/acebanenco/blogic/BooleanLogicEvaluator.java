package com.acebanenco.blogic;

import com.acebanenco.blogic.model.*;
import com.acebanenco.blogic.tree.ExpressionTreeTransformer;
import com.acebanenco.blogic.tree.ExpressionTreeWalker;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class BooleanLogicEvaluator {
    private final ExpressionTreeTransformer transformer = new ExpressionTreeTransformer();

    public List<BooleanExpression> evaluateExpression(BooleanExpression expression, Map<String, Integer> values) {
        List<BooleanExpression> expressions = new ArrayList<>();

        BooleanExpression lastExpression = transformer.transformTree(expression, x -> {
            if (x instanceof Identifier i) {
                return new Constant(values.get(i.getToken()));
            }
            return x;
        });
        expressions.add(lastExpression);

        ExpressionTreeWalker treeWalker = new ExpressionTreeWalker();
        while (!(lastExpression instanceof Constant)) {
            Map<BooleanExpression, BooleanExpression> replacements = new IdentityHashMap<>();
            treeWalker.walkTree(lastExpression, x -> {
                BooleanExpression y = simplifyExpression(x);
                if (y != x) {
                    replacements.put(x, y);
                }
            });
            lastExpression = transformer.transformTree(lastExpression, x -> replacements.getOrDefault(x, x));
            expressions.add(lastExpression);
        }
        return expressions;
    }

    private static BooleanExpression simplifyExpression(BooleanExpression x) {
        if (x instanceof Negation n && n.getRhs() instanceof Constant c) {
            return new Constant(c.getValue() == 0 ? 1 : 0);
        }
        if (x instanceof Alternative n && n.getLhs() instanceof Constant c1 && n.getRhs() instanceof Constant c2) {
            return new Constant(c1.getValue() | c2.getValue());
        }
        if (x instanceof Conjunction n && n.getLhs() instanceof Constant c1 && n.getRhs() instanceof Constant c2) {
            return new Constant(c1.getValue() & c2.getValue());
        }
        if (x instanceof Equality n && n.getLhs() instanceof Constant c1 && n.getRhs() instanceof Constant c2) {
            return new Constant(c1.getValue() == c2.getValue() ? 1 : 0);
        }
        if (x instanceof Implication n && n.getLhs() instanceof Constant c1 && n.getRhs() instanceof Constant c2) {
            return new Constant(c1.getValue() == 1 ? c2.getValue() : 1);
        }
        return x;
    }
}
