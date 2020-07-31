package com.capgemini.istat.vtlcompiler.vtlcommon.model.mapper;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponentRole;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VtlComponentMapper implements RowMapper<VtlComponent> {

    @Override
    public VtlComponent mapRow(ResultSet rs, int i) throws SQLException {
        VtlComponent vtlComponent = new VtlComponent();
        vtlComponent.setName(rs.getString("COMPONENTID"));
        vtlComponent.setType(VtlType.valueOf(rs.getString("DATATYPE").toUpperCase()));
        vtlComponent.setVtlComponentRole(VtlComponentRole.valueOf(rs.getString("COMPONENT_ROLE").toUpperCase()));
        vtlComponent.setDomainValue(rs.getString("VALUEDOMAIN"));
        vtlComponent.setDomainValueParent(rs.getString("VALUEDOMAIN_PARENT"));

        return vtlComponent;
    }
}
