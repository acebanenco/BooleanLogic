package com.acebanenco.blogic;

import com.acebanenco.blogic.model.BooleanExpression;
import com.acebanenco.blogic.parser.BooleanLogicParser;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BooleanLogicApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter expression: ");
        String formula = scanner.nextLine();
//        String formula = "!(p => q) & (r | !q)";

        BooleanLogicParser parser = new BooleanLogicParser();
        BooleanExpression expression = parser.parse(formula);

        BooleanLogicAnalyzer analyzer = new BooleanLogicAnalyzer();
        Set<String> identifiers = analyzer.getIdentifiers(expression);

        BooleanPermutationsBuilder permutationsBuilder = new BooleanPermutationsBuilder();
        List<Map<String,Integer>> permutations = permutationsBuilder.buildPermutations(identifiers);

        BooleanLogicEvaluator evaluator = new BooleanLogicEvaluator();
        for (Map<String, Integer> permutation : permutations) {
            String permStr = permutation.entrySet()
                    .stream()
                    .map(e -> e.getKey() + "=" + e.getValue())
                    .collect(Collectors.joining(", "));
            List<BooleanExpression> evaluationResults = evaluator.evaluateExpression(expression, permutation);
            String line = evaluationResults.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("  ->  "));
            System.out.println(" * [" + permStr + "]: " + line);
        }
    }

}
