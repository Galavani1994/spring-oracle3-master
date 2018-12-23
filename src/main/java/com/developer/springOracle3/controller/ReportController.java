package com.developer.springOracle3.controller;

import com.developer.springOracle3.entity.CPtableDto;
import com.developer.springOracle3.entity.Zamen;
import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.service.CPService;
import com.developer.springOracle3.util.GeneratePdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rp")
public class ReportController {
    @Autowired
    CPRepo cpRepo;
    @Autowired
    CPService cpService;


    @RequestMapping("/reportPage")
    public ModelAndView reportpage() {
        ModelAndView model = new ModelAndView("report");
        return model;
    }

    @RequestMapping("/resultreport")
    public ModelAndView resultreport(@RequestParam("fromdate") String kaladate, @RequestParam("todate") String todate) throws ParseException {
        ModelAndView mv = new ModelAndView("report");
        if (!kaladate.isEmpty() && !todate.isEmpty()) {
            mv.addObject("listcpbydate", cpService.findbykaladate(kaladate, todate));
            mv.addObject("salevalue", cpService.findsale(kaladate, todate));
            return mv;
        } else {
            return mv;
        }
    }

    @RequestMapping(value = "/print_report", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> saleReport(@RequestParam("fromdate") String kaladate,
                                                          @RequestParam("todate") String todate) throws IOException, ParseException {

        List<CPtableDto> list = cpService.findbykaladate(kaladate, todate);
        List<String> betweenDate = new ArrayList<>();
        betweenDate.add(kaladate);
        betweenDate.add(todate);


        ByteArrayInputStream bis = GeneratePdfReport.salesReport(list, betweenDate);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reportFile.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @PostMapping("/resultTest")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Zamen> testclient(@RequestBody Zamen zamen) {
        zamen.getZamenName();
        zamen.setZamenFamily("mahdi");
        return ResponseEntity.ok(zamen);
    }


}
