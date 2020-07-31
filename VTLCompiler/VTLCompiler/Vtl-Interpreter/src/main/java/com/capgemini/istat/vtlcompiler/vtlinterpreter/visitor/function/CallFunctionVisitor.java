package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.function;

import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.ConstantVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.terminal.OperatorIdVisitor;
import org.springframework.stereotype.Component;

@Component
public class CallFunctionVisitor extends InterpreterVisitor {

    OperatorIdVisitor operatorIdVisitor;
    ConstantVisitor constantVisitor;



}
