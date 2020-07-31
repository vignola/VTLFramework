package com.capgemini.istat.vtlcompiler.vtlcommon.model.mapper;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValueDomainMapper implements RowMapper<ValueDomain> {

    @Override
    public ValueDomain mapRow(ResultSet rs, int i) throws SQLException {
        ValueDomain valueDomain = new ValueDomain();
        valueDomain.setValueDomainName(rs.getString("VD_REF_ID"));
        valueDomain.setCode(rs.getString("ITEM_ID"));
        valueDomain.setDescription(rs.getString("NAME"));
        return valueDomain;
    }
}
