grammar Vtl_Mod;
import VtlTokens;

start:
    statement* EOF
;

/* statement */
statement:
    varID ASSIGN expr  EOL              # temporaryAssignment
    | varID PUT_SYMBOL expr EOL         # persistAssignment
    | expr EOL                          # expression
;
/* expression */
expr:
    LPAREN expr RPAREN													                                    # parenthesisExpr
    | expr MEMBERSHIP componentID                                                                           # membershipExpr
    | dataset=expr  QLPAREN  clause=datasetClause  QRPAREN                                                  # clauseExpr
    | IF  conditionalExpr=expr  THEN thenExpr=expr ELSE elseExpr=expr                                       # ifExpr
    | functions                                                                                             # functionsExpression
    | op=(PLUS|MINUS) right=expr                                                                            # unaryExpr
    | op=NOT right=expr                                                                                     # notExpr
    | left=expr op=MUL right=expr                                                                           # arithmeticExpr
    | left=expr op=DIV right=expr                                                                           # arithmeticExpr
    | left=expr op=PLUS right=expr                                                                          # arithmeticExpr
    | left=expr op=MINUS right=expr                                                                         # arithmeticExpr
    | left=expr CONCAT right=expr                                                                           # concatExpr
    | left=expr op=comparisonOperand  right=expr                                                            # comparisonExpr
    | left=expr op=(IN|NOT_IN)(lists|valueDomainID)                                                         # inNotInExpr
    | left=expr op=AND right=expr                                                                           # booleanExpr
    | left=expr op=(OR|XOR) right=expr									                            	    # booleanExpr
    | varID															                                        # varIdExpr
    | constant														                                        # constantExpr

;

/* functions */
functions:
     defOperators                       # defineExpr
    | joinOperators                     # joinFunctions
    | genericOperators                  # genericFunctions
    | stringOperators                   # stringFunctions
    | numericOperators                  # numericFunctions
    | comparisonOperators               # comparisonFunctions
    | timeOperators                     # timeFunctions
    | setOperators                      # setFunctions
    | hierarchyOperators                # hierarchyFunctions
    | validationOperators               # validationFunctions
    | conditionalOperators              # conditionalFunctions
    | aggrOperators                     # aggregateFunctions
    | aggrOperatorsGrouping             # aggregateFunctions
    | anFunction                        # analyticFunctions
;


/*------------------------------------------------------ Clauses----------------------------------------------- */
datasetClause:
    renameClause
    | aggrClause
    | filterClause
    | calcClause
    | keepClause
    | dropClause
    | pivotClause
    | unipivotClause
    | subspaceClause
;

renameClause:
    RENAME renameClauseItem (COMMA renameClauseItem)*
;

aggrClause:
    AGGREGATE aggregateClause groupingClause? havingClause?
;

filterClause:
    FILTER expr
;

calcClause:
    CALC calcClauseItem (COMMA calcClauseItem)*
;

keepClause:
    KEEP componentClause (COMMA componentClause)*
;

dropClause:
    DROP componentClause (COMMA componentClause)*
;

pivotClause:
    PIVOT varID COMMA varID
;

unipivotClause:
    UNPIVOT varID COMMA varID
;

subspaceClause:
    SUBSPACE subspaceClauseItem (COMMA subspaceClauseItem)*
;

/*------------------------------------------------------End Clauses----------------------------------------------- */

/************************************************** JOIN FUNCITONS -------------------------------------------*/

joinOperators:
     joinKeyword=(INNER_JOIN | LEFT_JOIN) LPAREN joinClause joinBody RPAREN                                # joinExpr
     | joinKeyword=(FULL_JOIN | CROSS_JOIN) LPAREN joinClauseWithoutClause joinBody RPAREN                   # joinExpr
;

/************************************************** END JOIN FUNCITONS -------------------------------------------*/
/* --------------------------------------------Define Functions------------------------------------------------- */
defOperators:
     defineOperator operatorID LPAREN (parameterItem (COMMA parameterItem)*)? RPAREN (RETURNS dataType)? IS expr endDefineOperator        # defOperator
    | defineDatapointRuleset rulesetID LPAREN rulesetSignature RPAREN IS ruleClauseDatapoint endDatapointRuleset                          # defDatapoinRuleset
    | defineHierarchicalRuleset rulesetID LPAREN hierRuleSignature RPAREN IS ruleClauseHierarchical endHierarchicalRuleset                # defHierarchical
;

defineOperator:
    DEFINE OPERATOR
;

endDefineOperator:
    END OPERATOR
;

defineDatapointRuleset:
    DEFINE DATAPOINT RULESET
;

defineHierarchicalRuleset:
    DEFINE HIERARCHICAL RULESET
;

endDatapointRuleset:
    END DATAPOINT RULESET
;

endHierarchicalRuleset:
    END HIERARCHICAL RULESET
;

parameterItem:
    varID dataType (DEFAULT constant)?
;

/* --------------------------------------------Define Functions------------------------------------------------- */

/*---------------------------------------------------FUNCTIONS-------------------------------------------------*/
genericOperators:
    operatorID LPAREN ((constant| OPTIONAL ) (COMMA (constant| OPTIONAL ))*)? RPAREN                                                                                            # callFunctionAtom
    |EVAL LPAREN routineName LPAREN (componentID|constant)? (COMMA (componentID|constant))* RPAREN (LANGUAGE STRING_CONSTANT)? (RETURNS outputParameterType)? RPAREN            # evalAtom
    |CAST LPAREN expr COMMA (basicScalarType|valueDomainName) (COMMA STRING_CONSTANT)? RPAREN                                                                                   # castExpr
;

stringOperators:
    TRIM LPAREN expr RPAREN												                        # trimAtom
    | LTRIM LPAREN expr RPAREN												                    # ltrimAtom
    | RTRIM LPAREN expr RPAREN												                    # rtrimAtom
    | UCASE LPAREN expr RPAREN												                    # ucaseAtom
    | LCASE LPAREN expr RPAREN												                    # lcaseAtom
    | SUBSTR LPAREN expr (COMMA optionalExpr)* RPAREN		                                    # substrAtom
    | REPLACE LPAREN expr COMMA param=expr ( COMMA optionalExpr)? RPAREN				        # replaceAtom
    | INSTR LPAREN expr COMMA param=expr ( COMMA optionalExpr)? (COMMA optionalExpr)? RPAREN	# instrAtom
    | LEN LPAREN expr RPAREN												                    # lenAtom
;

numericOperators:
    MOD LPAREN left = expr COMMA right = expr RPAREN										    # modAtom
    | ROUND LPAREN expr (COMMA optionalExpr)? RPAREN							                # roundAtom
    | TRUNC LPAREN expr (COMMA optionalExpr)? RPAREN							                # lnAtom
    | CEIL LPAREN expr RPAREN												                    # ceilAtom
    | FLOOR LPAREN expr RPAREN												                    # floorAtom
    | ABS LPAREN expr RPAREN												                    # minAtom
    | EXP LPAREN expr RPAREN												                    # expAtom
    | LN LPAREN expr RPAREN													                    # lnAtom
    | POWER LPAREN left=expr COMMA right=expr RPAREN										    # powerAtom
    | LOG LPAREN left=expr COMMA right=expr RPAREN										        # logAtom
    | SQRT LPAREN expr RPAREN												                    # sqrtAtom
;

comparisonOperators:
     BETWEEN LPAREN element=expr COMMA from=expr COMMA to=expr RPAREN	                    # betweenAtom
    | CHARSET_MATCH LPAREN left=expr COMMA  right=expr RPAREN							    # charsetMatchAtom
    | ISNULL LPAREN expr RPAREN												                # isNullAtom
    | EXISTS_IN LPAREN left=expr COMMA right=expr (COMMA retainType)? RPAREN                # existInAtom
;

timeOperators:
    PERIOD_INDICATOR LPAREN expr? RPAREN                                                                                                # periodAtom
    | FILL_TIME_SERIES LPAREN expr (COMMA (SINGLE|ALL))? RPAREN                                                                         # fillTimeAtom
    | FLOW_TO_STOCK LPAREN expr RPAREN										                                                            # flowToStockAtom
    | STOCK_TO_FLOW LPAREN expr RPAREN										                                                            # stockToFlowAtom
    | TIMESHIFT LPAREN expr COMMA INTEGER_CONSTANT RPAREN                                                                               # timeShiftAtom
    | TIME_AGG LPAREN STRING_CONSTANT (COMMA (STRING_CONSTANT| OPTIONAL ))? (COMMA (expr| OPTIONAL ))? (COMMA (FIRST|LAST))? RPAREN     # timeAggAtom
    | CURRENT_DATE LPAREN RPAREN                                                                                                        # currentDateAtom
;

setOperators:
    UNION LPAREN left=expr (COMMA expr)+ RPAREN                             # unionAtom
    | INTERSECT LPAREN left=expr (COMMA expr)+ RPAREN                       # intersectAtom
    | SETDIFF LPAREN left=expr COMMA right=expr RPAREN                      # setDiffAtom
    | SYMDIFF LPAREN left=expr COMMA right=expr RPAREN                      # symDiffAtom
;
/* hierarchy */         //TODO Refactoring
hierarchyOperators:
    HIERARCHY LPAREN expr COMMA IDENTIFIER (CONDITION componentID (COMMA componentID)*)? (RULE IDENTIFIER)? ((validationMode)| OPTIONAL )? ((RULE|DATASET|RULE_PRIORITY)| OPTIONAL )? ((COMPUTED|ALL)| OPTIONAL )? RPAREN
;

validationOperators:
    CHECK_DATAPOINT LPAREN expr COMMA IDENTIFIER (COMPONENTS componentID (COMMA componentID)*)? validationOutput? RPAREN												# validateDPruleset
  | CHECK_HIERARCHY LPAREN expr COMMA IDENTIFIER (conditionClause)? (RULE IDENTIFIER)? (validationMode)? (DATASET|DATASET_PRIORITY)? validationOutput? RPAREN 	        # validateHRruleset
  | CHECK LPAREN validationItem  (INVALID|ALL)? RPAREN													                                                                # validationSimple
;

conditionalOperators:
    NVL LPAREN left=expr COMMA right = expr RPAREN							# nvlAtom
;

aggrOperators:
    SUM LPAREN expr RPAREN                    # sumAggrComp
    | AVG LPAREN expr RPAREN                  # avgAggrComp
    | COUNT LPAREN expr? RPAREN               # countAggrComp
    | MEDIAN LPAREN expr RPAREN               # medianAggrComp
    | MIN LPAREN expr RPAREN                  # minAggrComp
    | MAX LPAREN expr RPAREN                  # maxAggrComp
    | RANK LPAREN expr RPAREN                 # rankAggrComp
    | STDDEV_POP LPAREN expr RPAREN           # stddevPopAggrComp
    | STDDEV_SAMP LPAREN expr RPAREN          # stddevSampAggrComp
    | VAR_POP LPAREN expr RPAREN              # varPopAggrComp
    | VAR_SAMP LPAREN expr RPAREN             # varSampAggrComp
;

aggrOperatorsGrouping:
    SUM LPAREN expr groupingClause havingClause? RPAREN                   # sumAggr
    | AVG LPAREN expr groupingClause havingClause? RPAREN                 # avgAggr
    | COUNT LPAREN expr groupingClause havingClause? RPAREN               # countAggr
    | MEDIAN LPAREN expr groupingClause havingClause? RPAREN              # medianAggr
    | MIN LPAREN expr groupingClause havingClause? RPAREN                 # minAggr
    | MAX LPAREN expr groupingClause havingClause? RPAREN                 # maxAggr
    | RANK LPAREN expr groupingClause havingClause? RPAREN                # rankAggr
    | STDDEV_POP LPAREN expr groupingClause havingClause? RPAREN          # stddevPopAggr
    | STDDEV_SAMP LPAREN expr groupingClause havingClause? RPAREN         # stddevSampAggr
    | VAR_POP LPAREN expr groupingClause havingClause? RPAREN             # varPopAggr
    | VAR_SAMP LPAREN expr groupingClause havingClause? RPAREN            # varSampAggr
;

 anFunction:
    SUM LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                                # sumAn
    | AVG LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                              # avgAn
    | COUNT LPAREN expr? OVER LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?) RPAREN RPAREN                           # countAn
    | MEDIAN LPAREN expr OVER LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                            # medianAn
    | MIN LPAREN expr OVER LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                               # minAn
    | MAX LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                              # maxAn
    | STDDEV_POP LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                       # stddevPopAn
    | STDDEV_SAMP LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                      # stddevSampAn
    | VAR_POP LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                          # varPopAn
    | VAR_SAMP LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                         # varSampAn
    | FIRST_VALUE LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                      # firstValueAn
    | LAST_VALUE LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause? windowing=windowingClause?)RPAREN RPAREN                       # lastValueAn
    | LAG  LPAREN expr (COMMA offet=INTEGER_CONSTANT(defaultValue=constant)?)?  OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause)   RPAREN RPAREN                                       # lagAn
    | LEAD  LPAREN expr (COMMA offet=INTEGER_CONSTANT(defaultValue=constant)?)?    OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause)RPAREN RPAREN                                       # leadAn
    | RANK LPAREN expr OVER  LPAREN (partiton=partitionByClause? orderBy=orderByClause) RPAREN RPAREN                                                             # rankAn
    | RATIO_TO_REPORT LPAREN expr OVER  LPAREN (partiton=partitionByClause) RPAREN RPAREN                                                                    # ratioToReportAn
;
/*---------------------------------------------------END FUNCTIONS-------------------------------------------------*/

/*-------------------------------------------------CLAUSE EXPRESSION------------------------------------------------*/
/*RENAME CLAUSE */
renameClauseItem:
    fromName=componentClause TO toName=componentClause
;

componentClause:
    varID MEMBERSHIP componentID    # componentMembership
    | componentID                   # component
    ;
/*END RENAME CLAUSE*/

/*AGGR CLAUSE*/
aggregateClause:
    aggrFunctionClause (COMMA aggrFunctionClause)*
;

aggrFunctionClause:
    (componentRole)? componentID  ASSIGN  aggrOperators
;
/*END AGGR CLAUSE*/

/*CALC CLAUSE*/
calcClauseItem:
    (componentRole)? componentID  ASSIGN  expr
;
/*END CALC CLAUSE*/

/*SUBSPACE CLAUSE*/
subspaceClauseItem
  :
  varID  EQ  constant
  ;
/*END SUBSPACE CLAUSE*/
/*----------------------------------------------END CLAUSE EXPRESSION--------------------------------------*/

/*---------------------------------------------JOIN CLAUSE EXPRESSION---------------------------------------*/

joinClauseWithoutClause:
    joinClauseItem (COMMA joinClauseItem)*
;

joinClause:
    joinClauseItem (COMMA joinClauseItem)* (USING componentID (COMMA componentID)*)?
;

joinClauseItem:
    expr (AS alias)?
;

joinBody:
    filterClause? (calcClause|joinApplyClause|aggrClause)? (keepClause|dropClause)? renameClause?
;

/* JOIN APPLY CLAUSE*/
joinApplyClause:
    APPLY expr
;
/* END JOIN APPLY CLAUSE*/

/*---------------------------------------------END JOIN CLAUSE EXPRESSION---------------------------------------*/

/*-----------------------------------------ANALYTIC CLAUSE -----------------------------------------------*/

partitionByClause:
    PARTITION BY componentID (COMMA componentID)*
;

orderByClause:
    ORDER BY orderByItem (COMMA orderByItem)*
;

orderByItem:
    componentID (ASC|DESC)?
;

windowingClause:
    ((DATA POINTS)|RANGE) BETWEEN from=limitClauseItem AND to=limitClauseItem
;

/*-----------------------------------------END ANALYTIC CLAUSE -----------------------------------------------*/
/* ------------------------------- GROUPING CLAUSE ------------------------------------*/
groupingClause:
    groupKeyword componentID (COMMA componentID)*   # groupByOrExcept
    | GROUP ALL expr                              # groupAll
  ;

havingClause:
  HAVING expr
  ;
/*------------------------------END GROUPING CLAUSE-------------------------------------*/

alias:
    IDENTIFIER
;

varID:
    IDENTIFIER
;

componentID:
    IDENTIFIER
;

validationOutput:
    INVALID|ALL_MEASURES|ALL
;

validationMode:
    NON_NULL|NON_ZERO|PARTIAL_NULL|PARTIAL_ZERO|ALWAYS_NULL|ALWAYS_ZERO
;

/* Rulesets Definition */

ruleClauseHierarchical:
    ruleItemHierarchical ( EOL  ruleItemHierarchical)*
 ;

ruleItemHierarchical:
    (IDENTIFIER  ':' )? codeItemRelation (erCode)? (erLevel)?
 ;

 hierRuleSignature:
    (VALUE_DOMAIN|VARIABLE) valueDomainSignature? RULE IDENTIFIER
 ;

 valueDomainSignature:
    CONDITION IDENTIFIER (AS IDENTIFIER)? (COMMA IDENTIFIER (AS IDENTIFIER)?)*
 ;

codeItemRelation:
    ( WHEN expr THEN )? codeItemRef codeItemRelationClause (codeItemRelationClause)*
;

codeItemRelationClause:
    (opAdd=( PLUS | MINUS  ))? IDENTIFIER ( QLPAREN  expr  QRPAREN )?
;

codeItemRef:
    IDENTIFIER (opComp=( EQ   | MT  | LT  | ME  | LE  ))?
;

ruleClauseDatapoint:
    ruleItemDatapoint ( EOL  ruleItemDatapoint)*
;

ruleItemDatapoint:
    (IDENTIFIER  ':' )? ( WHEN expr THEN )? expr (erCode)? (erLevel)?
;

rulesetSignature:
    (VALUE_DOMAIN|VARIABLE) varSignature (COMMA varSignature)*
;

varSignature:
    varID (AS IDENTIFIER)?
 ;


lists:
    GLPAREN  constant (COMMA constant)*  GRPAREN
;

validationItem:
    expr (codeErr=erCode)? (levelCode=erLevel)?
    | expr (codeErr=erCode)? (levelCode=erLevel)? imbalanceExpr
;

imbalanceExpr:
    IMBALANCE expr
;

conditionClause:
    CONDITION componentID (COMMA componentID)*
;

erCode:
    ERRORCODE  constant
;

erLevel:
    ERRORLEVEL  constant
 ;

limitClauseItem:
    INTEGER_CONSTANT PRECEDING
    | INTEGER_CONSTANT FOLLOWING
    | CURRENT DATA POINT
    | UNBOUNDED PRECEDING
    | UNBOUNDED FOLLOWING
;

comparisonOperand:
    MT
    | ME
    | LE
    | LT
    | EQ
    | NEQ
;

/* Conditional */

optionalExpr:
    expr | OPTIONAL
 ;

/* Role name*/
componentRole:
    MEASURE
    | COMPONENT
    | DIMENSION
    | ATTRIBUTE
    | viralAttribute
;

viralAttribute:
    VIRAL ATTRIBUTE
;

valueDomainID:
    IDENTIFIER
    ;

rulesetID:
    IDENTIFIER
;

operatorID:
    IDENTIFIER
;

routineName:
    IDENTIFIER
;

/*joinKeyword:
     |
;*/

groupKeyword:
    (GROUP BY) | (GROUP EXCEPT)
;

constant:
    INTEGER_CONSTANT | FLOAT_CONSTANT | BOOLEAN_CONSTANT | STRING_CONSTANT | NULL_CONSTANT
;

scalarType:
    (basicScalarType|valueDomainName)scalarTypeConstraint?((NOT)? NULL_CONSTANT)?
;

basicScalarType:
    STRING | INTEGER | NUMBER | BOOLEAN | DATE | TIME_PERIOD | DURATION | SCALAR | TIME
;

valueDomainName:
    IDENTIFIER
;

scalarTypeConstraint:
    ( QLPAREN  expr  QRPAREN )
    | ( GLPAREN  constant (COMMA constant)*  GRPAREN )
;

dataType:
    scalarType
    | componentType
    | datasetType
    | scalarSetType
    | operatorType
    | rulesetType
;

componentType:
    componentRole ( LT   scalarType  MT  )?
;

datasetType:
    DATASET ( GLPAREN compConstraint (COMMA compConstraint)*  GRPAREN  )?
;

compConstraint:
    componentType (componentID|multModifier)
;

multModifier:
    OPTIONAL  ( PLUS | MUL )?
;

rulesetType:
    RULESET
    | dpRuleset
    | hrRuleset
;

dpRuleset:
    DATAPOINT
    | (DATAPOINT_ON_VD  GLPAREN  prodValueDomains  GRPAREN )
    | (DATAPOINT_ON_VAR  GLPAREN  prodVariables  GRPAREN )
;

hrRuleset:
    HIERARCHICAL
    | (HIERARCHICAL_ON_VD ( GLPAREN  IDENTIFIER (LPAREN prodValueDomains RPAREN)?  GRPAREN )? )
    | (HIERARCHICAL_ON_VAR ( GLPAREN  varID (LPAREN prodVariables RPAREN)?  GRPAREN )? )
;

prodValueDomains:
    IDENTIFIER ( MUL  IDENTIFIER)*
;

prodVariables:
    varID ( MUL  varID)*
;

operatorType:
    inputParameterType ( MUL  inputParameterType)* POINTER outputParameterType
;

inputParameterType:
    scalarType
    | datasetType
    | componentType
;

outputParameterType:
    scalarType
    | datasetType
    | scalarSetType
    | rulesetType
    | componentType
;

scalarSetType:
    SET ( LT   scalarType  MT  )?
;

retainType:
    BOOLEAN_CONSTANT
    | ALL
;

