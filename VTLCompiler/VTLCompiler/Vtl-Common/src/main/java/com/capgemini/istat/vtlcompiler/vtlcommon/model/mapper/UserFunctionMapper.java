package com.capgemini.istat.vtlcompiler.vtlcommon.model.mapper;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFunctionMapper implements RowMapper<UserFunction> {

    @Override
    public UserFunction mapRow(ResultSet rs, int i) throws SQLException {
        UserFunction userFuncRow = new UserFunction();
        userFuncRow.setFunctionName(rs.getString("OPERATOR_ID"));
        userFuncRow.setFunctionType(VtlUserFunctionType.valueOf(rs.getString("OPERATOR_TYPE")));
        userFuncRow.setFunctionContent(rs.getString("OPERATOR_BODY"));
        return userFuncRow;
    }
}
