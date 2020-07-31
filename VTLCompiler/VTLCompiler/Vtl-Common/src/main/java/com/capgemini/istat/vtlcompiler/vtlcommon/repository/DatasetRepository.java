package com.capgemini.istat.vtlcompiler.vtlcommon.repository;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.dataset.VtlDataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface DatasetRepository extends JpaRepository<VtlDataset, Long> {

    VtlDataset findByNameIgnoreCase(String name);

    List<VtlDataset> findAllByNameIgnoreCase(String name);

    List<VtlDataset> findAllByName(String name);

    Integer countAllByNameIgnoreCase(String name);

    Integer countAllByName(String name);

    @Query(value = "call NEXT VALUE FOR  PUBLIC.temporary_dataset_identifier", nativeQuery = true)
    Long getTemporaryId();

    @Transactional
    Integer deleteAllByIsPersistentEqualsAndRequestUuid(Boolean isPersistent, UUID requestUuid);

    @Transactional
    Integer deleteAllByRequestUuid(UUID requestUuid);

    List<VtlDataset> findAllByIsPersistentEquals(Boolean isPersistent);

    void deleteById(Long id);

    @Transactional
    Integer deleteByName(String vtlDatasetName);

    List<VtlDataset> findAllByOrderByNameAsc();
}
