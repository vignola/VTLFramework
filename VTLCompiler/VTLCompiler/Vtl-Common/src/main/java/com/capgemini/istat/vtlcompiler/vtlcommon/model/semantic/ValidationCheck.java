package com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic;

import java.io.Serializable;

/**
 * La classe offre tutti i possibili messaggi di errore di natura semantica che possono essere generati e tutte le validazioni possibili.
 * tramite una mappa offerta dalla classe Operator le validazioni vengono settate nell'oggetto validation check
 * e inviate nei motori di validazione
 * I codici dei messaggi fanno riferimnento alle informazion che si trovano sul file di proprietà message.properties
 */
public enum ValidationCheck implements Serializable {
    //GENERIC
    UNEXPECTED_ERROR("VTL001", "exception.unexpected"),
    GENERIC_TRASLATION_ERROR("VTL002", "generic.traslation.error"),
    PACKAGE_READ_ERROR("VTL003", "package.read.error"),
    INVALID_ACTIVE_PROFILE("VTL004", "invalid.active.profile"),
    NO_RULES("VTL005", "no.rules"),
    NOT_ALLOWED("VTL006", "not.allowed"),
    NOT_ALLOWED_CAST("VTL007", "not.allowed.cast"),
    NEED_CAST_MASK("VTL008", "need.cast.mask"),

    //DATASET
    IS_SUPERSET("VTL007", "is.superset"),
    HAS_COMMON_MEASURE("VTL008", "has.common.measure"),
    HAS_ONLY_MEASURE_NUMBER("VTL009", "has.only.measure.number"),
    HAS_AT_LEAST_ONE_IDENTIFIER("VTL010", "has.at.least.one.identifier"),
    HAS_ONLY_ONE_MEASURE("VTL011", "has.only.one.measure"),
    HAS_ONLY_MEASURE_INTEGER_OR_NUMBER("VTL012", "has.only.measure.integer.or.number"),
    HAS_ONLY_MEASURE_INTEGER("VTL013", "has.only.measure.integer"),
    HAS_ONLY_MEASURE_BOOLEAN("VTL014", "has.only.measure.boolean"),
    DATASET_EXIST("VTL015", "dataset.exist"),
    HAS_DATASET_THIS_NAME("VTL016", "has.dataset.this.name"),
    HAS_MEASURES_IMPLICITLY_CASTABLE_TO_STRING("VTL017", "has.measures.implicitly.castable.to.string"),
    HAS_ONE_MEASURE_OF_SAME_TYPE("VTL018", "has.one.measure.of.same.type"),
    NOT_ALLOWED_SCALAR_TO_SCALAR("VTL019", "not.allowed.scalar.to.scalar"),
    NOT_ALLOWED_SCALAR_TO_DATASET("VTL020", "not.allowed.scalar.to.dataset"),
    HAS_SAME_STRUCTURE("VTL021", "has.same.structure"),
    HAS_ONLY_IDENTIFIER("VTL022", "has.only.identifier"),
    ARE_NOT_IDENTIFIERS("VTL023", "are.not.identifiers"),
    ARE_ALL_IDENTIFIERS("VTL024", "are.all.identifiers"),
    HAS_AT_LEAST_ONE_IDENTIFIER_TIME_PERIOD("VTL025", "has.at.least.one.identifier.time.period"),
    HAS_ONLY_ONE_IDENTIFIER_TIME_PERIOD("VTL026", "has.only.one.identifier.time.period"),
    HAS_AT_LEAST_ONE_IDENTIFIER_TIME("VTL027", "has.at.least.one.identifier.time"),
    HAS_ONLY_ONE_IDENTIFIER_TIME("VTL028", "has.only.one.identifier.time"),
    COMPONENT_EXISTS("VTL029", "components.exist"),
    COMPONENT_NOT_EXISTS("VTL030", "components.not.exist"),
    TEMPORARY_DATASET("VTL031", "temporary.dataset"),
    HAS_COMMON_SUPERSET("VTL032", "has.common.superset"),
    HAS_COMMON_IDENTIFICATION("VTL033", "has.common.identification"),
    HAS_DUPLICATE_MEASURES("VTL034", "has.duplicate.measures"),
    HAS_ONLY_ONE_MEASURE_TYPE("VTL035", "has.only.one.measure.type"),
    USING_COMPONENT("VTL036", "using.component"),
    USING_MANDATORY("VTL037", "using.mandatory"),
    NO_CANDIDATE_TO_DATASET("VTL038", "no.candidate.to.dataset"),
    USING_IDENTIFIERS("VTL039", "using.identifiers"),
    HAS_ONLY_ONE_BY_ROLE("VTL040", "has.only.one.by.role"),
    HAS_AT_LEAST_ONE_BY_ROLE("VTL041", "has.at.least.one.by.role"),
    HAS_TYPE_AND_ROLE("VTL042", "has.type.and.role"),
    HAS_VALUE_DOMAIN_AND_ROLE("VTL043", "has.value.domain.and.role"),
    COMPONENT_NAME_YET_USED("VTL043", "component.name.yet.used"),

    //COMPONENT
    NOT_ALLOWED_COMPONENT("VTL044", "not.allowed.component"),
    IS_NUMBER_OR_INTEGER_TYPE("VTL045", "is.number.or.integer.type"),
    IS_IMPLICIT_CASTABLE_TO_STRING("VTL046", "is.implicit.castable.to.string"),
    IS_INTEGER("VTL047", "is.integer"),
    IS_NUMBER("VTL048", "is.number"),
    IS_BOOLEAN("VTL049", "is.boolean"),
    IS_TIME_PERIOD("VTL050", "is.time.period"),
    IS_TIME("VTL051", "is.time"),
    IS_DURATION("VTL052", "is.duration"),
    IS_SAME_TYPE_MEASURES("VTL053", "is.same.type.measures"),
    IS_IDENTICAL_TYPE("VTL054", "is.identical.type"),
    IS_SAME_TYPE_SCALARS("VTL055", "is.same.type.scalars"),
    TECHNICAL_ERROR("VTL056", "technical.error"),
    HAVING_EXCEPTION("VTL057", "having.exception"),


    //DEFINE OPERATOR
    FUNCTION_NOT_EXIST("VTL058", "function.not.exist"),
    FUNCTION_TYPE_WRONG("VTL059", "function.type.wrong"),
    FUNCTION_TYPE_NOT_OPTIONAL("VTL060", "function.type.not.optional"),
    WRONG_PARAMETER_INVOCATION("VTL061", "wrong.parameter.invocation"),
    WRONG_PARAMETER_TYPE_INVOCATION("VTL062", "wrong.parameter.type.invocation"),
    NEED_ALIAS_NAME("VTL063", "need.alias.name"),
    ALIAS_REPEATED("VTL064", "alias.repeated"),
    PARAMETER_NAME_REPEATED("VTL065", "parameter.repeated"),
    PARAMETER_REPEATED("VTL066", "parameter.repeated"),


    //VALIDATION OPERATOR
    INCOMPATIBLE_PARAMETER("VTL067", "incompatible.parameter.invocation"),
    INCOMPATIBLE_VALUE_DOMAIN("VTL068", "incompatible.parameter.valuedomain"),
    RULE_NOT_FOUND("VTL069", "rule.not.found"),
    WRONG_PARAMETER_NAME("VTL070", "wrong.parameter.name"),
    VALUEDOMAIN_NOT_FOUND("VTL071", "valuedomain.not.found"),
    VALUEDOMAIN_VALUE_NOT_FOUND("VTL072", "valuedomain.value.not.found"),
    USING_NOT_COMMON_IDENTIFIERS("VTL073", "using.not.common.identifiers"),
    USING_NOT_ALL_OTHER_IDENTIFIERS("VTL074", "using.not.all.other.identifiers"),
    USING_NOT_IDENTIFIERS("VTL075", "using.not.identifiers"),
    USING_COMPONENT_NOT_EXIST("VTL076", "using.component.not.exist");

    private final String code;
    private final String message;

    private ValidationCheck(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

