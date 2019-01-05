package com.developer.springOracle3.util;

import com.developer.springOracle3.entity.Customer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;

public class CustomerInfoPdf {
    public static ByteArrayInputStream customerPrint(Customer customer) throws IOException, DocumentException, ParseException {

        Document document = new Document();
        document.open();

        ByteArrayOutputStream out = new ByteArrayOutputStream();


        BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\SMITRA.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font=new Font(bf,12);



            document.add(new Paragraph("hello"));


            document.close();





        return new ByteArrayInputStream(out.toByteArray());
    }
}
