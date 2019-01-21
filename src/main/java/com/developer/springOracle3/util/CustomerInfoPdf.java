package com.developer.springOracle3.util;

import com.developer.springOracle3.entity.Customer;
import com.developer.springOracle3.util.date.FDate;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class CustomerInfoPdf {
    public static ByteArrayInputStream customerPrint(Customer customer) throws IOException, DocumentException, ParseException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Adding Font
            BaseFont bf =BaseFont.createFont("C://Windows/Fonts/SMITRA.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf);
            //-------------------------Create Header Table---------------------
            PdfPTable tHeader = new PdfPTable(1);

            PdfPCell cell;
            Paragraph pHoly = new Paragraph("بسمه تعالی", font);

            cell = new PdfPCell(pHoly);
            cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tHeader.addCell(cell);
            tHeader.setSpacingAfter(20);

            //Create Title IN Header
            PdfPTable tTitle = new PdfPTable(4);
            tTitle.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            tTitle.setWidths(new int[]{6, 3, 19, 6});

            PdfPCell tcell;

            tcell = new PdfPCell(new Phrase("کد مشتری:", font));
            tcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            tcell.setBorder(Rectangle.NO_BORDER);
            tTitle.addCell(tcell);

            tcell = new PdfPCell(new Phrase(customer.getCuid(), font));
            tcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            tcell.setBorder(Rectangle.NO_BORDER);
            tTitle.addCell(tcell);

            tcell = new PdfPCell(new Phrase("تاریخ:", font));
            tcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            tcell.setBorder(Rectangle.NO_BORDER);
            tTitle.addCell(tcell);

            Date date = new Date();
            String dates = FDate.Gregorian_to_Farsi(date);
            tcell = new PdfPCell(new Phrase(dates, font));
            tcell.setBorder(Rectangle.NO_BORDER);
            tTitle.addCell(tcell);


            Paragraph lineP = new Paragraph("------------------------------------------------------------------------------------------------------------------");
            lineP.setAlignment(Element.ALIGN_CENTER);
            //------------------------------------------header^-------------------------------------------------

            //----------------------create TableContent----------------------------------------------------------
            PdfPTable CoTable = new PdfPTable(4);
            CoTable.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

            PdfPCell contentCell;

            contentCell = new PdfPCell(new Phrase("نام", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase(customer.getFirstName(), font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase("شماره موبایل", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase(customer.getMobileNum(), font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase("نام خانوادگی", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase(customer.getLastName(), font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);


            contentCell = new PdfPCell(new Phrase("شماره تلفن", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase(customer.getPhoneNum(), font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase("آدرس", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase(customer.getAddressname(), font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase("تاریخ عضویت", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            Date date2 = customer.getRegisterDate();
            String stringDate = FDate.Gregorian_to_Farsi(date2);
            contentCell = new PdfPCell(new Phrase(stringDate, font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase("مانده حساب", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase(String.valueOf(customer.getMande()), font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            contentCell = new PdfPCell(new Phrase("تاریخ آخرین مراجعه", font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);

            Date date1=customer.getLastCome();
            contentCell = new PdfPCell(new Phrase(FDate.Gregorian_to_Farsi(date1), font));
            contentCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            contentCell.setPaddingBottom(5);
            CoTable.addCell(contentCell);


            PdfWriter.getInstance(document, out);
            document.open();
            document.add(tHeader);
            document.add(tTitle);
            document.add(lineP);
            document.add(CoTable);
            document.close();
        } catch (DocumentException ex) {
            //Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());

    }
}
