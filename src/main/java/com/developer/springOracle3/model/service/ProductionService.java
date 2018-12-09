package com.developer.springOracle3.model.service;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.ProductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class ProductionService {

    @Autowired
    private ProductionRepo productionRepo;
    @Autowired
    private CPRepo cpRepo;


    public void save(Production production) throws MyException {


        Production save = productionRepo.save(production);
    }

    public void delete(Production production) {
        productionRepo.deleteById(production.getId());
    }





    public List<Production> findAll() {
        return productionRepo.findAll();
    }

}
