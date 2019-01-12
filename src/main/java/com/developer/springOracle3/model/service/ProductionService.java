package com.developer.springOracle3.model.service;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.entity.Production;
import com.developer.springOracle3.entity.ProductionDto;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.repository.ProductionRepo;
import com.developer.springOracle3.util.ObjectConverter;
import com.developer.springOracle3.util.date.FDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class ProductionService {

    @Autowired
    private ProductionRepo prRepo;
    @Autowired
    private CPRepo cpRepo;


    public List<ProductionDto> findAll() throws ParseException {
        List<Production> productions = prRepo.findAll();
        List<ProductionDto> productions1 = new ArrayList<>();
        for (Production production : productions) {
            ProductionDto productionDto = ObjectConverter.instance.getCloneObject(production, ProductionDto.class);
            productionDto.setTarikh(FDate.Gregorian_to_Farsi(production.getTarikh()));
            productionDto.setRemainMeter(prRepo.remainMeter(production.getPrid()));
            productions1.add(productionDto);
        }
        return productions1;
    }

    public void save(Production production) throws MyException {
        if (production.getId() == null) {
            production.setTarikh(FDate.Farsi_to_Gregorian(production.getTarikh()));
            prRepo.save(production);
        } else {
            production.setTarikh(FDate.Farsi_to_Gregorian(production.getTarikh()));
            prRepo.save(production);
        }
    }

    public void delete(Production production) {
        prRepo.deleteById(production.getId());
    }

    public List<ProductionDto> showProduction(String value) throws ParseException {

        List<Production> productions = prRepo.findByPrNameOrPrid(value,value);
        List<ProductionDto> productions1 = new ArrayList<>();
        for (Production production : productions) {
            ProductionDto productionDto = ObjectConverter.instance.getCloneObject(production, ProductionDto.class);
            productionDto.setTarikh(FDate.Gregorian_to_Farsi(production.getTarikh()));
            productionDto.setRemainMeter(prRepo.remainMeter(production.getPrid()));
            productions1.add(productionDto);
        }
        return productions1;
    }


}
