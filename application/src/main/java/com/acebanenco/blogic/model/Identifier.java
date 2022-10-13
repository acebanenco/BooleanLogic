package com.acebanenco.blogic.model;

public class Identifier implements BooleanExpression {
    String token;

    public Identifier(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
