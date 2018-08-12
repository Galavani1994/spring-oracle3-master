package com.developer.springOracle3.model.service;

import com.developer.springOracle3.entity.CPtable;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.ProductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CPService {

    @Autowired
    private CPRepo cpRepo;



    public void save(CPtable cPtable) {
        CPtable save = cpRepo.save(cPtable);
    }
}
