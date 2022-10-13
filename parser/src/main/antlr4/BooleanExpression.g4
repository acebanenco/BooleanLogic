grammar BooleanExpression;

program:	expression EOF ;
expression:	NOT rhs=expression                              # negation
    |	lhs=expression AND rhs=expression                   # conjunction
    |	lhs=expression OR rhs=expression                    # alternative
    |	lhs=expression (implOp=IMPL|eqOp=EQ) rhs=expression # implicationOrEquality
    |	id=ID                                               # idExpression
    |	'(' rhs=expression ')'                              # subExpression
    ;

NOT : '!' ;
AND : '&' ;
OR : '|' ;
IMPL : '=>' ;
EQ : '<=>' ;

ID  : [a-zA-Z] ;
WS  : [ \t\r\n]+ -> skip;
