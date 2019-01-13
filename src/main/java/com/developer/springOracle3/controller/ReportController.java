package com.developer.springOracle3.controller;

import com.developer.springOracle3.model.repository.CPRepo;
import com.developer.springOracle3.model.service.CPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@RestController
@RequestMapping("/rp")
public class ReportController {
    @Autowired
    CPRepo cpRepo;
    @Autowired
    CPService cpService;

    @RequestMapping("/resultreport")
    public ModelAndView resultreport(@RequestParam("fromdate") String kaladate, @RequestParam("todate") String todate) throws ParseException {
        ModelAndView mv = new ModelAndView("report");
        if (!kaladate.isEmpty() && !todate.isEmpty()) {

            return mv;
        } else {
            return mv;
        }
    }

   /* @RequestMapping(value = "/print_report", method = RequestMethod.GET,
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
    }*/



}
