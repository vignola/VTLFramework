package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlComponent;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.mapper.UserFunctionMapper;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.mapper.ValueDomainMapper;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.mapper.VtlComponentMapper;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * la classe offre tutte le funzionalità per l'interrogazione della vista di interfaccia creata sul database fisico
 */
@Service
public class VtlDatabaseService {

    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dbDatasource")
    public void setDataSource(DataSource dbDatasource) {

        this.jdbcTemplateObject = new JdbcTemplate(dbDatasource);
    }

    /**
     * il metodo dato in ingresso un nome di un valueDomain ricercato carica tutti i valori collegati
     *
     * @param valueDomainName il nome del valueDomain ricercato
     * @return la lista dei valori del valueDomain ricercato
     */
    public List<ValueDomain> getValueDomain(String valueDomainName) {
        String SQL = "select * from WW_VALUE_DOMAIN_CODE where LANGUAGE='it' and upper(VD_REF_ID)=upper( ? ) order by ITEM_ID";
        List<ValueDomain> valueDomainList = jdbcTemplateObject.query(SQL, new ValueDomainMapper(), valueDomainName);

        return valueDomainList;
    }

    /**
     * il metodo dato in ingresso un nome di dataset, lo cerca sulla vista e lo restituisce
     *
     * @param name       il nome del dataset ricercato
     * @param ignoreCase questo flag decide se la ricerca del dataset deve essere fatta in case sensitive
     * @return il VtlDataset ricostruito dalla lista
     */
    public VtlDataset findByName(String name, boolean ignoreCase) {
        String SQL;
        if (ignoreCase)
            SQL = "select * from WW_DATASET_INFO T where upper(T.DATASETID)=upper( ? )";
        else
            SQL = "select * from WW_DATASET_INFO T where T.DATASETID= ? ";
        VtlDataset valueDataset = new VtlDataset();
        List<VtlComponent> vtlComponents = jdbcTemplateObject.query(SQL, new VtlComponentMapper(), name);

        valueDataset.setName(name);
        valueDataset.setDescription(name);
        valueDataset.setPersistent(Boolean.TRUE);
        valueDataset.addComponentsList(vtlComponents);

        return vtlComponents.isEmpty() ? null : valueDataset;
    }

    /**
     * Dato un nome di dataset conta le occorrenze in cui il dataset è presente sulla vista
     *
     * @param name       il nome del dataset ricercato
     * @param ignoreCase questo flag decide se la ricerca del dataset deve essere fatta in case sensitive
     * @return il numero di occorrenze in cui è presente il dataset
     */
    public Integer countAllByName(String name, boolean ignoreCase) {
        String SQL;
        if (ignoreCase)
            SQL = "select count(T.DATASETID) from WW_DATASET_INFO T where upper(T.DATASETID)=upper( ? )";
        else
            SQL = "select count(T.DATASETID) from WW_DATASET_INFO T where T.DATASETID= ? ";
        Integer result = jdbcTemplateObject.queryForObject(SQL, new Object[]{name}, Integer.class);

        return result;
    }

    /**
     * il metodo dato un nome di value domain e il valore verifica che questo sia presente sulla vista e lo restituisce
     *
     * @param valueDomainName il nome del valuedomain ricercato
     * @param code            il valore del value domain ricercato
     * @return il valueDomain cercato sulla vista. null se non viene trovato nulla.
     */
    public ValueDomain getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(String valueDomainName, String code) {
        String SQL = "select * from WW_VALUE_DOMAIN_CODE where LANGUAGE='it' and upper(VD_REF_ID)=upper( ? ) and upper(ITEM_ID)=upper( ? ) order by ITEM_ID";
        List<ValueDomain> valueDomainList = jdbcTemplateObject.query(SQL, new ValueDomainMapper(), valueDomainName, code);
        return valueDomainList.isEmpty() ? null : valueDomainList.get(0);
    }

    /**
     * Il metodo effettua una ricerca sulla vista delle funzioni tramite il nome funzione. Restituisce null se non trova nulla.
     *
     * @param functionName il nome della funzione da ricercare
     * @return la funzione trovata. Null se non è presente.
     */
    //USER FUNCTIONS
    public UserFunction getUserFunctionByName(String functionName) {

        String SQL = "select * from USER_DEFINE_OPERATORS T where T.OPERATOR_ID= ? ";
        List<UserFunction> userFunctions = jdbcTemplateObject.query(SQL, new RowMapper<UserFunction>() {
            @Override
            public UserFunction mapRow(ResultSet rs, int i) throws SQLException {
                UserFunction userFuncRow = new UserFunction();
                userFuncRow.setFunctionName(functionName);
                String operatorTye = rs.getString("OPERATOR_TYPE");
                VtlUserFunctionType vtlUserFunctionType = null;
                if (operatorTye.equalsIgnoreCase("0"))
                    vtlUserFunctionType = VtlUserFunctionType.OPERATOR_FUNCTION;
                if (operatorTye.equalsIgnoreCase("1"))
                    vtlUserFunctionType = VtlUserFunctionType.DATAPOINT_FUNCTION;
                if (operatorTye.equalsIgnoreCase("2"))
                    vtlUserFunctionType = VtlUserFunctionType.HIERARCHICAL_FUNCTION;
                userFuncRow.setFunctionType(vtlUserFunctionType);
                userFuncRow.setFunctionContent(rs.getString("OPERATOR_BODY"));
                return userFuncRow;
            }
        }, functionName);

        return userFunctions.isEmpty() ? null : userFunctions.get(0);
    }

    /**
     * il metodo restituisce tutte le funzioni presenti sulla vista
     *
     * @return una lista delle funzioni trovate
     */
    public List<UserFunction> getAllUserFunctions() {
        String SQL = "select * from USER_DEFINE_OPERATORS T ";
        List<UserFunction> userFunctions = jdbcTemplateObject.query(SQL, new UserFunctionMapper());
        return userFunctions;
    }

    /**
     * il metodo restituisce tutte le funzioni presenti sulal vista filtrate per tipo lista
     *
     * @param functionType il tipo della funzione da ricercare
     * @return una lista di funzioni trovate.
     */
    public List<UserFunction> getAllUserFunctionsByType(String functionType) {
        String SQL = "select * from USER_DEFINE_OPERATORS T where upper(T.OPERATOR_TYPE)=upper( ? ) ORDER BY T.OPERATOR_ID ASC";
        List<UserFunction> userFunctions = jdbcTemplateObject.query(SQL, new UserFunctionMapper(), functionType);
        return userFunctions;
    }


}
