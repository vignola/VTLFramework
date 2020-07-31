package com.capgemini.istat.vtlcompiler.vtlcommon.repository;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ValueDomainRepository extends JpaRepository<ValueDomain, Long> {

    List<ValueDomain> getAllByValueDomainNameIgnoreCase(String valueDomainName);

    ValueDomain getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(String valueDomainName, String code);

    @Transactional
    Integer deleteByValueDomainName(String valueDomainName);
}
