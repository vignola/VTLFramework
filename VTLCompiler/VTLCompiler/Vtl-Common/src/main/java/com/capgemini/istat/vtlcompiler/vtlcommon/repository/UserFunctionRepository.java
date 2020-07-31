package com.capgemini.istat.vtlcompiler.vtlcommon.repository;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.UserFunction;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.define.VtlUserFunctionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserFunctionRepository extends JpaRepository<UserFunction, Long> {


    UserFunction findByFunctionName(String functionName);

    List<UserFunction> findAllByFunctionTypeOrderByFunctionName(VtlUserFunctionType functionType);

    void deleteById(Long id);

    @Transactional
    Integer deleteByFunctionName(String functionName);

    @Transactional
    Integer deleteAllByRequestUuid(UUID requestUuid);

}
