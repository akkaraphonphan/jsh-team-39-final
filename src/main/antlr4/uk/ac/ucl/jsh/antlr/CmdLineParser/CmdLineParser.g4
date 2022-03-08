grammar CmdLineParser;

compileUnit
    :   seq EOF
    |   command EOF
    ;

command
    :   pipe
    |   call
    ;

pipe
    :   call1=call PIPE call2=call   #pipeBase
    |   pipe PIPE call               #pipeRecursive
    ;

seq
    :   seq SEMI command                      #seqRecursive
    |   cmd1=command SEMI cmd2=command        #seqBase
    ;

call    :   (NON_KEYWORD | LT | GT | single_quoted | double_quoted | backquoted | WS)+;

single_quoted   :   '\'' squote_content '\'';
squote_content  :   (NON_KEYWORD | keyword | WS | '"' | '`')*;

double_quoted   :   '"' dquote_content '"';
dquote_content  :   (NON_KEYWORD | keyword | WS | '\'' | backquoted)*; 

backquoted      :   '`' content = bquote_content '`';
bquote_content  :   (NON_KEYWORD | keyword | WS | '"' | '\'')*;

keyword : SEMI | PIPE | GT | LT;

WS             : [ \t];
NON_KEYWORD    : ~[ \t"'`\n\r;|><];
SEMI        : ';';
PIPE        : '|';
GT          : '>';
LT          : '<';