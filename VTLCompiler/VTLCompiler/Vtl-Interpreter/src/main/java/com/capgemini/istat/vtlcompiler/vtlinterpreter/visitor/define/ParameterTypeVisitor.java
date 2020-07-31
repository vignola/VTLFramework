package com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.define;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.define.operator.datatype.*;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.InterpreterVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.component.ComponentRoleVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.type.BasicScalarTypeVisitor;
import com.capgemini.istat.vtlcompiler.vtlinterpreter.visitor.type.ScalarTypeContraintVisitor;
import com.capgemini.istat.vtlcompiler.vtlparser.VtlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParameterTypeVisitor extends InterpreterVisitor {

    private BasicScalarTypeVisitor basicScalarTypeVisitor;
    private ScalarTypeContraintVisitor scalarTypeContraintVisitor;
    private ComponentRoleVisitor componentRoleVisitor;


    @Autowired
    public void setBasicScalarTypeVisitor(BasicScalarTypeVisitor basicScalarTypeVisitor) {
        this.basicScalarTypeVisitor = basicScalarTypeVisitor;
    }

    @Autowired
    public void setScalarTypeContraintVisitor(ScalarTypeContraintVisitor scalarTypeContraintVisitor) {
        this.scalarTypeContraintVisitor = scalarTypeContraintVisitor;
    }

    @Autowired
    public void setComponentRoleVisitor(ComponentRoleVisitor componentRoleVisitor) {
        this.componentRoleVisitor = componentRoleVisitor;
    }



    @Override
    public VtlExpression visitRulesetType(VtlParser.RulesetTypeContext ctx) {
        return null;
    }

    @Override
    public VtlScalarType visitScalarType(VtlParser.ScalarTypeContext ctx) {
        VtlScalarType vtlScalarType = new VtlScalarType();
        if (ctx.basicScalarType() != null) {
            vtlScalarType.setVtlType(
                    basicScalarTypeVisitor.visitBasicScalarType(ctx.basicScalarType())
            );
        }
        if (ctx.valueDomainName() != null && ctx.valueDomainName().IDENTIFIER() != null) {
            vtlScalarType.setValueDomain(ctx.valueDomainName().IDENTIFIER().getText());
        }
        if (ctx.scalarTypeConstraint() != null) {
            vtlScalarType.setVtlConstraint(
                    scalarTypeContraintVisitor.visit(ctx.scalarTypeConstraint())
            );
        }
        return vtlScalarType;
    }


    @Override
    public VtlComponentType visitComponentType(VtlParser.ComponentTypeContext ctx) {
        VtlComponentType vtlComponentType = new VtlComponentType();
        vtlComponentType.setComponentRole(
                componentRoleVisitor.visitComponentRole(ctx.componentRole())
        );
        if (ctx.scalarType() != null) {
            vtlComponentType.setScalarType(
                    visitScalarType(ctx.scalarType())
            );
        }
        return vtlComponentType;
    }

    @Override
    public VtlDatasetType visitDatasetType(VtlParser.DatasetTypeContext ctx) {
        VtlDatasetType vtlDatasetType = new VtlDatasetType();
        for (VtlParser.CompConstraintContext compConstraintContext : ctx.compConstraint()) {
            vtlDatasetType.getComponentConstraints().addLast(
                    visitCompConstraint(compConstraintContext)
            );
        }
        return vtlDatasetType;
    }

    @Override
    public VtlScalarSetType visitScalarSetType(VtlParser.ScalarSetTypeContext ctx) {
        VtlScalarSetType vtlScalarSetType = new VtlScalarSetType();
        if (ctx.scalarType() != null) {
            vtlScalarSetType.setScalarType(
                    visitScalarType(ctx.scalarType())
            );
        }
        return vtlScalarSetType;
    }

    @Override
    public VtlComponentConstraint visitCompConstraint(VtlParser.CompConstraintContext ctx) {
        VtlComponentConstraint vtlComponentConstraint = new VtlComponentConstraint();
        vtlComponentConstraint.setVtlComponentType(
                visitComponentType(ctx.componentType())
        );
        if (ctx.componentID() != null && ctx.componentID().IDENTIFIER() != null) {
            vtlComponentConstraint.setComponentName(
                    ctx.componentID().IDENTIFIER().toString()
            );
        }
        if (ctx.multModifier() != null) {
            if (ctx.multModifier().MUL() != null) {
                vtlComponentConstraint.setVtlComponentMultiplicity(VtlComponentMultiplicity.ZERO_TO_MANY);
            } else if (ctx.multModifier().PLUS() != null) {
                vtlComponentConstraint.setVtlComponentMultiplicity(VtlComponentMultiplicity.ONE_TO_MANY);
            } else {
                vtlComponentConstraint.setVtlComponentMultiplicity(VtlComponentMultiplicity.ONLY_ONE);
            }
        }
        return vtlComponentConstraint;
    }

    @Override
    public VtlDataType visitOutputParameterType(VtlParser.OutputParameterTypeContext ctx) {
        if (ctx.componentType() != null)
            return visitComponentType(ctx.componentType());
        if (ctx.scalarType() != null)
            return visitScalarType(ctx.scalarType());
        if (ctx.datasetType() != null)
            return visitDatasetType(ctx.datasetType());
        return null;
    }

}
