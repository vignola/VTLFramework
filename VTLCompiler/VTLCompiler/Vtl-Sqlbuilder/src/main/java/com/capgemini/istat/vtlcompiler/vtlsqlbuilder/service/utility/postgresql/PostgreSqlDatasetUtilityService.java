package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.postgresql;

import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlDatasetUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Offre le funzionalità di manipolazione dei Dataset e SqlDataset specifici per PostgreSQL
 *
 * @see PostgreSqlObjectUtilityService
 * @see PostgreSqlComponentUtilityService
 */
@Service
public class PostgreSqlDatasetUtilityService extends CommonSqlDatasetUtilityService {

    @Autowired
    public void setSqlObjectUtilityService(PostgreSqlObjectUtilityService postgreSqlObjectUtilityService) {
        super.setSqlObjectUtilityService(postgreSqlObjectUtilityService);
    }

    @Autowired
    public void setSqlComponentUtilityService(PostgreSqlComponentUtilityService postgreSqlComponentUtilityService) {
        super.setSqlComponentUtilityService(postgreSqlComponentUtilityService);
    }

}
