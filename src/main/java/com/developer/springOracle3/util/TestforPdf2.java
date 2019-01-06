package com.developer.springOracle3.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class TestforPdf2 {
    public static void main(String[] args) throws IOException, DocumentException, ParseException {

        Document document=new Document();
        PdfWriter pdfWriter= PdfWriter.getInstance(document,new FileOutputStream("D:\\New folder\\itexsample.pdf"));
        document.open();

        // Adding Font
        BaseFont bf = BaseFont.createFont("/fonts/nazanin/B Mitra.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf);

        //-------------------------Create Header Table---------------------
        PdfPTable tHeader=new PdfPTable(1);

        PdfPCell cell;
        Paragraph pHoly=new Paragraph("بسمه تعالی",font);

        cell=new PdfPCell(pHoly);
        cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        cell.setPaddingBottom(10);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tHeader.addCell(cell);
        tHeader.setSpacingAfter(20);

        //Create Title IN Header
        PdfPTable tTitle=new PdfPTable(3);
        tTitle.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        tTitle.setWidths(new int[] {6,3,25});

        PdfPCell tcell;

        tcell=new PdfPCell(new Phrase("گزارش فروش ",font));
        tcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        tcell.setBorder(Rectangle.NO_BORDER);
        tTitle.addCell(tcell);

        tcell=new PdfPCell(new Phrase("تاریخ:",font));
        tcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        tcell.setBorder(Rectangle.NO_BORDER);
        tTitle.addCell(tcell);

        Date date=new Date();
        Date date1=FDate.gro_to_farsi(date);
        String dates=FDate.formatter_to_string(date1);
        tcell=new PdfPCell(new Phrase(dates,font));
        tcell.setBorder(Rectangle.NO_BORDER);
        tTitle.addCell(tcell);


        Paragraph lineP=new Paragraph("------------------------------------------------------------------------------------------------------------------");
        lineP.setAlignment(Element.ALIGN_CENTER);
        //------------------------------------------header^-------------------------------------------------

        PdfPTable table1 = new PdfPTable(3);
        table1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        table1.setSpacingBefore(15);

        PdfPCell cell1;
        cell1 = new PdfPCell(new Phrase("کدمشتری", font));
        cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setPaddingBottom(5);
        table1.addCell(cell1);

        cell1 = new PdfPCell(new Phrase("نام", font));
        cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setPaddingBottom(5);
        table1.addCell(cell1);

        cell1 = new PdfPCell(new Phrase("نام خانوادگی", font));
        cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setPaddingBottom(5);
        table1.addCell(cell1);

        for (int i = 1; i <5 ; i++) {
            cell1 = new PdfPCell(new Phrase(String.valueOf(i),font));
            cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPaddingBottom(5);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("مهدی"+String.valueOf(i), font));
            cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPaddingBottom(5);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("گلوانی", font));
            cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPaddingBottom(5);
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);
        }


        document.add(tHeader);
        document.add(tTitle);
        document.add(lineP);
        document.add(table1);
        document.close();
    }
}
