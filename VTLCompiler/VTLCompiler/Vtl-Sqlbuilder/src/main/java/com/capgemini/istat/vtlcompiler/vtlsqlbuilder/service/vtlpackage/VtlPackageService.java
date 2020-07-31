package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.vtlpackage;

import com.capgemini.istat.vtlcompiler.vtlcommon.constant.ConstantUtility;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.TraslationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * La classe offre contiene le funzionalità  per rendere disponibili i package SQL
 */
@Service
public class VtlPackageService {
    public static final Logger logger = LogManager.getLogger(VtlPackageService.class);

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Metodo obsoleto. Non si consiglia l'utilizzo. Restituisce sotto forma di stringa il package. Possibili side effect
     *
     * @return
     * @throws TraslationException
     */
    public String getVtlPackage() throws TraslationException {
        String packageName = "";
        String profileActive = "";
        String resultSQL = null;

        try {
            profileActive = environment.getActiveProfiles()[0];
            Resource resource = getResource(profileActive);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
            resultSQL = stringBuilder.toString();
        } catch (IOException e) {
            logger.error(e);
            TraslationException traslationException = new TraslationException(ValidationCheck.PACKAGE_READ_ERROR.getMessage());
            traslationException.setCode(ValidationCheck.PACKAGE_READ_ERROR.getCode());
            return getString(packageName, traslationException);
        } catch (IllegalArgumentException e) {
            logger.error(e);
            TraslationException traslationException = new TraslationException(ValidationCheck.INVALID_ACTIVE_PROFILE.getMessage());
            traslationException.setCode(ValidationCheck.INVALID_ACTIVE_PROFILE.getCode());
            getString(profileActive, traslationException);
        }

        return resultSQL;
    }

    private String getString(String packageName, TraslationException traslationException) throws TraslationException {
        String[] parameters = new String[1];
        parameters[0] = packageName;
        traslationException.setParameters(parameters);
        traslationException.setCommand("getVtlPackage");
        throw traslationException;
    }

    /**
     * Offre il package richiesto sotto forma di Resource all'invocante
     *
     * @param packageName il nome del package richiesto
     * @return una resource contenente il package
     */
    public Resource getResource(String packageName) {
        if (packageName.equalsIgnoreCase("oracleSql")) {
            packageName = ConstantUtility.ORACLE_PACKAGE;
        } else if (packageName.equalsIgnoreCase("sqlServer")) {
            packageName = ConstantUtility.SQLSERVER_PACKAGE;
        } else if (packageName.equalsIgnoreCase("mySql")) {
            packageName = ConstantUtility.MYSQL_PACKAGE;
        } else if (packageName.equalsIgnoreCase("postgreSql")) {
            packageName = ConstantUtility.POSTGRESQL_PACKAGE;
        }
        return new ClassPathResource(packageName);
    }
}
