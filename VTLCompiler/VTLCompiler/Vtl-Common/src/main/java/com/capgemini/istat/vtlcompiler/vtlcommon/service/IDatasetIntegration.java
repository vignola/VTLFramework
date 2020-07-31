package com.capgemini.istat.vtlcompiler.vtlcommon.service;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.valuedomain.ValueDomain;

import java.util.List;
import java.util.UUID;

public interface IDatasetIntegration {

	//DatasetRepository Methods
	void save(VtlDataset result);

    VtlDataset findByNameIgnoreCase(String name, UUID requestUuid);

    VtlDataset findByName(String name, UUID requestUuid);

    Integer countAllByNameIgnoreCase(String name, UUID requestUuid);

    Integer countAllByName(String name, UUID requestUuid);

    Long getTemporaryId();

    Integer deleteAllByIsPersistentEquals(Boolean isPersistent, UUID requestUuid);

    List<VtlDataset> findAllByIsPersistentEquals(Boolean isPersistent);

    void deleteById(Long id);

    Integer deleteByName(String vtlDatasetName);

    List<VtlDataset> findAllByOrderByNameAsc();
    
    List<VtlDataset> findAll(); //class VtlDatasetController
    
    void deleteAll(); //class VtlDatasetController
    
    //ValueDomainRepository Methods
    void save(ValueDomain result);
    
    List<ValueDomain> getAllByValueDomainNameIgnoreCase(String valueDomainName);

    ValueDomain getByValueDomainNameIgnoreCaseAndCodeIgnoreCase(String valueDomainName, String code);
}
