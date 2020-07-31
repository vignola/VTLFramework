package com.capgemini.istat.vtlcompiler.vtlinterpreter.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.statement.VtlStatement;

import java.util.List;

public interface IParseTreeService {

    List<VtlStatement> visitTree(String commandStatements);

}
