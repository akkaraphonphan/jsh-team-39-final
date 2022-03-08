grammar CallParser;

compileUnit
    :   WS* arguments WS* EOF
    ;

arguments
    :   argument
    |   argument WS+ left_arguments = arguments
    |   cmd = arguments WS* io_operator = GT WS* file = arguments 
    |   io_operator = GT WS* file = arguments WS+ cmd = arguments
    |   cmd = arguments WS* io_operator = LT WS* file = arguments  
    |   io_operator = LT WS* file = arguments WS+ cmd = arguments
    ;

argument
    :   non_quote = non_quoted argument?
    |   quoted argument?
    ;

non_quoted
    :   NON_KEYWORD+
    ;

quoted
    :   single_quoted
    |   double_quoted
    |   backquoted
    ;

single_quoted   :   '\'' squote_content '\'';
squote_content  :   (NON_KEYWORD | keyword | WS | '"' | '`')*;

double_quoted   :   '"' dquote_content '"';
dquote_content  :   content = (NON_KEYWORD | SEMI | PIPE | GT | LT | WS | '\'') dquote_content
                |   backquoted dquote_content
                |  
                ;

backquoted      :   '`' content = bquote_content '`';
bquote_content  :   (NON_KEYWORD | keyword | WS | '"' | '\'')*;

keyword : SEMI | PIPE | GT | LT;

WS             : [ \t];
NON_KEYWORD    : ~[ \t"'`\n\r;|><];
SEMI           : ';';
PIPE           : '|';
GT             : '>';
LT             : '<';