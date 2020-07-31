package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.oracle;

import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlComponentUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * La classe offre manipolazioni di Component e SqlComponent al fine di giungere alla traduzione verso Oracle
 *
 * @see OracleSqlObjectUtilityService
 */
@Service
public class OracleSqlComponentUtilityService extends CommonSqlComponentUtilityService {

    @Autowired
    public void setSqlObjectUtilityService(OracleSqlObjectUtilityService oracleSqlObjectUtilityService) {
        super.setSqlObjectUtilityService(oracleSqlObjectUtilityService);
    }


}
