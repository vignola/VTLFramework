package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.postgresql;

import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.utility.CommonSqlComponentUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Offre le funzionalità di manipolazione dei Component e SqlComponent specifici per PostgreSQL
 *
 * @see PostgreSqlObjectUtilityService
 */
@Service
public class PostgreSqlComponentUtilityService extends CommonSqlComponentUtilityService {

    @Autowired
    public void setSqlObjectUtilityService(PostgreSqlObjectUtilityService postgreSqlObjectUtilityService) {
        super.setSqlObjectUtilityService(postgreSqlObjectUtilityService);
    }

}
