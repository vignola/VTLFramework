package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.oracle;

import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlDatasetUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * La classe offre manipolazioni di dataset e SqlDataset al fine di giungere alla traduzione verso Oracle
 *
 * @see OracleSqlObjectUtilityService
 * @see OracleSqlComponentUtilityService
 */
@Service
public class OracleSqlDatasetUtilityService extends CommonSqlDatasetUtilityService {

    @Autowired
    public void setSqlObjectUtilityService(OracleSqlObjectUtilityService oracleSqlObjectUtilityService) {
        super.setSqlObjectUtilityService(oracleSqlObjectUtilityService);
    }

    @Autowired
    public void setSqlComponentUtilityService(OracleSqlComponentUtilityService oracleSqlComponentUtilityService) {
        super.setSqlComponentUtilityService(oracleSqlComponentUtilityService);
    }


}
