package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.expression;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlConstantExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.constant.VtlOptional;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlMembership;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlParenthesisExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.booleans.VtlBooleanUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlClauseOperator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.VtlDatasetClause;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.comparison.VtlInExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.conditional.VtlIfExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.function.VtlUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.numeric.VtlNumericUnaryExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.string.VtlStringBinaryExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.service.IExpressionFactoryService;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.clause.DatasetClauseVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function.FunctionVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.VarIdVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpressionVisitor extends InterpreterVisitor<VtlExpression> {

    private IExpressionFactoryService expressionFactoryService;

    private ComponentIdVisitor componentIdVisitor;
    private VarIdVisitor varIdVisitor;
    private ListsVisitor listsVisitor;
    private DatasetClauseVisitor datasetClauseVisitor;
    private ConstantVisitor constantVisitor;
    private FunctionVisitor functionVisitor;

    @Autowired
    public void setFunctionVisitor(FunctionVisitor functionVisitor) {
        this.functionVisitor = functionVisitor;
    }

    @Autowired
    public void setVarIdVisitor(VarIdVisitor varIdVisitor) {
        this.varIdVisitor = varIdVisitor;
    }

    @Autowired
    public void setExpressionFactoryService(IExpressionFactoryService expressionFactoryService) {
        this.expressionFactoryService = expressionFactoryService;
    }

    @Autowired
    public void setComponentIdVisitor(ComponentIdVisitor componentIdVisitor) {
        this.componentIdVisitor = componentIdVisitor;
    }

    @Autowired
    public void setListsVisitor(ListsVisitor listsVisitor) {
        this.listsVisitor = listsVisitor;
    }

    @Autowired
    public void setDatasetClauseVisitor(DatasetClauseVisitor datasetClauseVisitor) {
        this.datasetClauseVisitor = datasetClauseVisitor;
    }

    @Autowired
    public void setConstantVisitor(ConstantVisitor constantVisitor) {
        this.constantVisitor = constantVisitor;
    }

    @Override
    public VtlExpression visitParenthesisExpr(VtlParser.ParenthesisExprContext ctx) {
        VtlParenthesisExpression parenthesisExpression = new VtlParenthesisExpression();
        if (ctx.expr() != null) {
            parenthesisExpression.setExpression(visit(ctx.expr()));
            parenthesisExpression.setCommand(ctx.getText());
            return parenthesisExpression;
        }
        return null;
    }

    @Override
    public VtlExpression visitMembershipExpr(VtlParser.MembershipExprContext ctx) {
            VtlMembership vtlMembership = new VtlMembership();
            getVariables().put(KeyVariables.MEMBERSHIP_IN_CLAUSE, true);
            if (ctx.MEMBERSHIP() != null) {
                Operator operator = Operator.MEMBERSHIP;
                if (getVariables().get(KeyVariables.DATASET_IN_CLAUSE) != null) {
                    getVariables().put(KeyVariables.MEMBERSHIP_IN_CLAUSE, true);
                    operator = Operator.MEMBERSHIP_IN_CLAUSE;
                }
                if (ctx.expr() != null)
                    vtlMembership.setVtlExpression(
                            visit(ctx.expr())
                    );
                if (ctx.simpleComponentId() != null)
                    componentIdVisitor.setVariables(getVariables());
                vtlMembership.setVtlComponentId(
                        componentIdVisitor.visitSimpleComponentId(ctx.simpleComponentId())
                );

                vtlMembership.setOperator(operator);
                getVariables().remove(KeyVariables.MEMBERSHIP_IN_CLAUSE);
                vtlMembership.setCommand(ctx.getText());
            }
            return vtlMembership;

    }

    @Override
    public VtlExpression visitFunctionsExpression(VtlParser.FunctionsExpressionContext ctx) {
        functionVisitor.setVariables(getVariables());
        return functionVisitor.visit(ctx.functions());
    }

    @Override
    public VtlExpression visitUnaryExpr(VtlParser.UnaryExprContext ctx) {
        if (ctx.op.getText().equalsIgnoreCase("NOT")) {
            return getVtlBooleanUnaryExpression(ctx.NOT(), ctx.right != null, visit(ctx.right), ctx.getText());
        } else {
            return getVtlSignExpr(ctx.op, ctx.MINUS(), ctx.right != null, visit(ctx.right), ctx.getText());
        }
    }

    @Override
    public VtlExpression visitArithmeticExprOrConcat(VtlParser.ArithmeticExprOrConcatContext ctx) {
        if (ctx.op.getText().equalsIgnoreCase("||")) {
            return getConcatExpr(ctx.expr() != null, visit(ctx.left), visit(ctx.right), ctx.getText());
        } else {
            return getVtlArithmeticExpr(visit(ctx.left), visit(ctx.right), ctx.op, ctx.getText());
        }
    }

    @Override
    public VtlExpression visitArithmeticExpr(VtlParser.ArithmeticExprContext ctx) {
        return getVtlArithmeticExpr(visit(ctx.left), visit(ctx.right), ctx.op, ctx.getText());
    }

    private VtlExpression getVtlArithmeticExpr(VtlExpression visit, VtlExpression visit2, Token op, String text) {
        VtlExpression vtlExpression;
        logger.debug("visitArithmeticExpr -> ");
        VtlExpression leftExpression = visit;
        VtlExpression rightExpression = visit2;
        logger.debug("visitArithmeticExpr -> Op -> " + op.getText());
        vtlExpression = expressionFactoryService.arithmeticFactory(op.getText().toUpperCase(), leftExpression, rightExpression);
        vtlExpression.setCommand(text);
        return vtlExpression;
    }

    @Override
    public VtlExpression visitComparisonExpr(VtlParser.ComparisonExprContext ctx) {
        return getComparisonExpr(visit(ctx.left), visit(ctx.right), ctx.comparisonOperand(), ctx.getText());
    }

    private VtlExpression getComparisonExpr(VtlExpression visit, VtlExpression visit2, VtlParser.ComparisonOperandContext comparisonOperandContext, String text) {
        VtlExpression vtlComparisonExpression;
        logger.debug("visitComparisonExpr -> ");
        VtlExpression vtlExpressionLeft = visit;
        VtlExpression vtlExpressionRight = visit2;
        vtlComparisonExpression = expressionFactoryService.comparisonFactory(comparisonOperandContext.getText(), vtlExpressionLeft, vtlExpressionRight);
        vtlComparisonExpression.setCommand(text);
        return vtlComparisonExpression;
    }

    @Override
    public VtlInExpression visitInNotInExpr(VtlParser.InNotInExprContext ctx) {
        return getInNotInExpr(ctx.IN(), ctx.NOT_IN(), visit(ctx.left), ctx.lists(), ctx.valueDomainID(), ctx.getText());
    }


    @Override
    public VtlBooleanBinaryExpression visitBooleanExpr(VtlParser.BooleanExprContext ctx) {
        VtlBooleanBinaryExpression vtlExpression = getVtlBooleanBinaryExpression(visit(ctx.left), visit(ctx.right), ctx.op.getText(), ctx.getText());
        return vtlExpression;
    }

    @Override
    public VtlClauseOperator visitClauseExpr(VtlParser.ClauseExprContext ctx) {
        VtlClauseOperator vtlClauseOperator = new VtlClauseOperator();
        if (ctx.clause != null) {
            getVariables().put(KeyVariables.DATASET_IN_CLAUSE, true);
            datasetClauseVisitor.setVariables(getVariables());
            VtlDatasetClause vtlDatasetClause = datasetClauseVisitor.visitDatasetClause(ctx.clause);
            getVariables().remove(KeyVariables.DATASET_IN_CLAUSE);
            vtlDatasetClause.setDataset(
                    visit(ctx.expr())
            );
            vtlClauseOperator.setVtlDatasetClause(vtlDatasetClause);
            vtlClauseOperator.setCommand(ctx.getText());
        }

        return vtlClauseOperator;
    }

    @Override
    public VtlExpression visitVarIdExpr(VtlParser.VarIdExprContext ctx) {
        if (ctx.varID() != null) {
            varIdVisitor.setVariables(getVariables());
            return varIdVisitor.visitVarID(ctx.varID());
        }
        return null;
    }

    @Override
    public VtlConstantExpression visitConstantExpr(VtlParser.ConstantExprContext ctx) {
        VtlConstantExpression vtlConstantExpression = getVtlConstant(ctx.constant(), ctx.getText());
        return vtlConstantExpression;
    }

    @Override
    public VtlExpression visitIfExpr(VtlParser.IfExprContext ctx) {
        return getIfExpression(visit(ctx.conditionalExpr), visit(ctx.thenExpr), visit(ctx.elseExpr), ctx.getText());
    }

    @Override
    public VtlExpression visitOptionalExpr(VtlParser.OptionalExprContext ctx) {
        if (ctx.expr() != null) {
            return visit(ctx.expr());
        }
        if (ctx.OPTIONAL() != null) {
            return new VtlOptional();
        }
        return null;
    }

    @Override
    public VtlExpression visitParenthesisExprComp(VtlParser.ParenthesisExprCompContext ctx) {
        VtlParenthesisExpression parenthesisExpression = new VtlParenthesisExpression();
        if (ctx.exprComponent() != null) {
            parenthesisExpression.setExpression(visit(ctx.exprComponent()));
            parenthesisExpression.setCommand(ctx.getText());
            return parenthesisExpression;
        }
        return null;
    }

    @Override
    public VtlExpression visitFunctionsExpressionComp(VtlParser.FunctionsExpressionCompContext ctx) {
        functionVisitor.setVariables(getVariables());
        return functionVisitor.visit(ctx.functionsComponents());
    }

    @Override
    public VtlExpression visitUnaryExprComp(VtlParser.UnaryExprCompContext ctx) {
        if (ctx.op.getText().equalsIgnoreCase("NOT")) {
            return getVtlBooleanUnaryExpression(ctx.NOT(), ctx.right != null, visit(ctx.right), ctx.getText());
        } else {
            return getVtlSignExpr(ctx.op, ctx.MINUS(), ctx.right != null, visit(ctx.right), ctx.getText());
        }
    }

    @Override
    public VtlExpression visitArithmeticExprComp(VtlParser.ArithmeticExprCompContext ctx) {
        return getVtlArithmeticExpr(visit(ctx.left), visit(ctx.right), ctx.op, ctx.getText());
    }

    @Override
    public VtlExpression visitArithmeticExprOrConcatComp(VtlParser.ArithmeticExprOrConcatCompContext ctx) {
        if (ctx.op.getText().equalsIgnoreCase("||")) {
            return getConcatExpr(ctx.exprComponent() != null, visit(ctx.left), visit(ctx.right), ctx.getText());
        } else {
            return getVtlArithmeticExpr(visit(ctx.left), visit(ctx.right), ctx.op, ctx.getText());
        }
    }

    @Override
    public VtlExpression visitComparisonExprComp(VtlParser.ComparisonExprCompContext ctx) {
        return getComparisonExpr(visit(ctx.left), visit(ctx.right), ctx.comparisonOperand(), ctx.getText());
    }

    @Override
    public VtlInExpression visitInNotInExprComp(VtlParser.InNotInExprCompContext ctx) {
        return getInNotInExpr(ctx.IN(), ctx.NOT_IN(), visit(ctx.left), ctx.lists(), ctx.valueDomainID(), ctx.getText());
    }

    @Override
    public VtlBooleanBinaryExpression visitBooleanExprComp(VtlParser.BooleanExprCompContext ctx) {
        VtlBooleanBinaryExpression vtlExpression = getVtlBooleanBinaryExpression(visit(ctx.left), visit(ctx.right), ctx.op.getText(), ctx.getText());
        return vtlExpression;
    }

    @Override
    public VtlExpression visitIfExprComp(VtlParser.IfExprCompContext ctx) {
        return getIfExpression(visit(ctx.conditionalExpr), visit(ctx.thenExpr), visit(ctx.elseExpr), ctx.getText());
    }

    @Override
    public VtlExpression visitCompId(VtlParser.CompIdContext ctx) {
        return componentIdVisitor.visitComponentID(ctx.componentID());
    }

    @Override
    public VtlConstantExpression visitConstantExprComp(VtlParser.ConstantExprCompContext ctx) {
        VtlConstantExpression vtlConstantExpression = getVtlConstant(ctx.constant(), ctx.getText());
        return vtlConstantExpression;
    }

    private VtlConstantExpression getVtlConstant(VtlParser.ConstantContext constantContext, String text) {
        VtlConstantExpression vtlConstantExpression = new VtlConstantExpression();
        vtlConstantExpression.setVtlConstant(
                constantVisitor.visitConstant(constantContext)
        );
        vtlConstantExpression.setCommand(text);
        return vtlConstantExpression;
    }

    private VtlExpression getIfExpression(VtlExpression visit, VtlExpression visit2, VtlExpression visit3, String text) {
        VtlIfExpression vtlIfExpression = new VtlIfExpression();
        vtlIfExpression.setCondition(
                visit
        );
        vtlIfExpression.setThenExpr(
                visit2
        );
        vtlIfExpression.setElseExpr(
                visit3
        );
        vtlIfExpression.setOperator(Operator.IF_THEN_ELSE);
        vtlIfExpression.setConditionalOperator(Operator.IF_THEN_ELSE_CONDITION);
        vtlIfExpression.setCommand(text);
        return vtlIfExpression;
    }

    private VtlBooleanBinaryExpression getVtlBooleanBinaryExpression(VtlExpression vtlExpressionLeft, VtlExpression vtlExpressionRight, String op, String text) {
        VtlBooleanBinaryExpression vtlExpression;
        vtlExpression = expressionFactoryService.booleanFactory(
                op,
                vtlExpressionLeft,
                vtlExpressionRight
        );
        vtlExpression.setCommand(text);
        return vtlExpression;
    }

    private VtlInExpression getInNotInExpr(TerminalNode in, TerminalNode terminalNode, VtlExpression visit, VtlParser.ListsContext lists, VtlParser.ValueDomainIDContext valueDomainIDContext, String text) {
        VtlInExpression vtlExpression = new VtlInExpression();
        if (in != null) {
            logger.debug("visitLogicalExpr -> " + in.getText());
            vtlExpression.setOperator(Operator.IN);
        } else {
            logger.debug("visitLogicalExpr -> " + terminalNode.getText());
            vtlExpression.setOperator(Operator.NOT_IN);
        }
        vtlExpression.setVtlElement(visit);
        if (lists != null) {
            listsVisitor.setVariables(getVariables());
            (vtlExpression).setVtlConstantList(listsVisitor.visitLists(lists));
        }
        if (valueDomainIDContext != null) {
            vtlExpression.setValueDomain(valueDomainIDContext.getText());
        }
        vtlExpression.setCommand(text);
        return vtlExpression;
    }

    private VtlStringBinaryExpression getConcatExpr(boolean b, VtlExpression visitLeft, VtlExpression visitRight, String text) {
        VtlStringBinaryExpression vtlExpression = new VtlStringBinaryExpression();
        logger.debug("visitBinaryExpr -> ");
        if (b) {
            vtlExpression.setLeftExpression(visitLeft);
            vtlExpression.setRightExpression(visitRight);
        }
        vtlExpression.setOperator(Operator.CONCATENATION);
        vtlExpression.setCommand(text);
        return vtlExpression;
    }


    private VtlExpression getVtlSignExpr(Token op, TerminalNode minus, boolean b, VtlExpression visit, String text) {
        VtlUnaryExpression vtlUnaryExpression = null;
        if (op != null) {
            vtlUnaryExpression = new VtlNumericUnaryExpression();
            logger.debug("visitUnaryExpr -> OP -> " + op.getText());
            if (minus != null) {
                vtlUnaryExpression.setOperator(Operator.UNARY_NEGATIVE);
            } else {
                vtlUnaryExpression.setOperator(Operator.UNARY_POSITIVE);
            }
            if (b)
                vtlUnaryExpression.setVtlExpression(visit);

            vtlUnaryExpression.setCommand(text);
        }
        return vtlUnaryExpression;
    }


    private VtlBooleanUnaryExpression getVtlBooleanUnaryExpression(TerminalNode not, boolean b, VtlExpression visit, String text) {
        VtlBooleanUnaryExpression vtlUnaryExpression = null;
        if (not != null) {
            vtlUnaryExpression = new VtlBooleanUnaryExpression();
            vtlUnaryExpression.setOperator(Operator.NOT);

            if (b)
                vtlUnaryExpression.setVtlExpression(visit);

            vtlUnaryExpression.setCommand(text);
        }
        return vtlUnaryExpression;
    }


}
