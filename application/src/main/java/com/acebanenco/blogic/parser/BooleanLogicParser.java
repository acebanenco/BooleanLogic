package com.acebanenco.blogic.parser;

import com.acebanenco.blogic.model.BooleanExpression;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class BooleanLogicParser {

    public BooleanExpression parse(String formula) {
        CharStream input = CharStreams.fromString(formula);
        TokenSource source = new com.acebanenco.blogic.parser.BooleanExpressionLexer(input);
        TokenStream stream = new BufferedTokenStream(source);

        com.acebanenco.blogic.parser.BooleanExpressionParser parser = new com.acebanenco.blogic.parser.BooleanExpressionParser(stream);
        com.acebanenco.blogic.parser.BooleanExpressionParser.ProgramContext program = parser.program();

        ParseTreeWalker treeWalker = new ParseTreeWalker();
        BooleanLogicBuilder listener = new BooleanLogicBuilder();
        treeWalker.walk(listener, program);
        return listener.getResult();
    }

}
