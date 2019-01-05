package com.developer.springOracle3.util;

import com.developer.springOracle3.entity.CPtableDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    public static ByteArrayInputStream salesReport(List<CPtableDto> list,List<String> betweendate) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();


        try {

            BaseFont bf = BaseFont.createFont("/fonts/nazanin/B Mitra.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font=new Font(bf,12);


            //-------------------------Create Header Table---------------------
            PdfPTable tHeader=new PdfPTable(1);

            PdfPCell cell;
            Paragraph pHoly=new Paragraph("بسمه تعالی",font);

            cell=new PdfPCell(pHoly);
            cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
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
            tcell=new PdfPCell(new Phrase(dates));
            tcell.setBorder(Rectangle.NO_BORDER);
            tTitle.addCell(tcell);

            //Adding from date to date Table
            PdfPTable tDate=new PdfPTable(4);
            tDate.setWidthPercentage(40);
            tDate.setWidths(new int[] {6,3,6,3});
            tDate.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            tDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            tDate.setSpacingBefore(20);

            PdfPCell pdfPCell;

            pdfPCell=new PdfPCell(new Phrase("ازتاریخ:",font));
            pdfPCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            pdfPCell.setBorder(Rectangle.NO_BORDER);
            tDate.addCell(pdfPCell);

            pdfPCell=new PdfPCell(new Phrase(betweendate.get(0),font));
            pdfPCell.setBorder(Rectangle.NO_BORDER);
            tDate.addCell(pdfPCell);

            pdfPCell=new PdfPCell(new Phrase("تاتاریخ:",font));
            pdfPCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            pdfPCell.setBorder(Rectangle.NO_BORDER);
            tDate.addCell(pdfPCell);

            pdfPCell=new PdfPCell(new Phrase(betweendate.get(1),font));
            pdfPCell.setBorder(Rectangle.NO_BORDER);
            tDate.addCell(pdfPCell);


            Paragraph lineP=new Paragraph("------------------------------------------------------------------------------------------------------------------");
            lineP.setAlignment(Element.ALIGN_CENTER);
            //------------------------------------------header^-------------------------------------------------


            PdfPTable table = new PdfPTable(6);
            table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{6, 6, 3,3,3,6});
            table.setSpacingBefore(15);


            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("کدمشتری", font));
            hcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("نام", font));
            hcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("نام کالا", font));
            hcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("تحویلی", font));
            hcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("پرداختی", font));
            hcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("تاریخ", font));
            hcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);



            for (CPtableDto cPtableDto : list) {

                PdfPCell cell1;

                cell1 = new PdfPCell(new Phrase(cPtableDto.getCuid()));
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell1);

                cell1 = new PdfPCell(new Phrase(cPtableDto.getPrid()));
                cell1.setPaddingLeft(5);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell1);

                cell1 = new PdfPCell(new Phrase(cPtableDto.getPrName(),font));
                cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPaddingRight(5);
                table.addCell(cell1);

                cell1 = new PdfPCell(new Phrase(String.valueOf(cPtableDto.getMetercp())));
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPaddingRight(5);
                table.addCell(cell1);

                cell1 = new PdfPCell(new Phrase(cPtableDto.getPay()));
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPaddingRight(5);
                table.addCell(cell1);

                String date11=FDate.formatter_to_string(cPtableDto.getKaladate());

                cell1 = new PdfPCell(new Phrase(date11));
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPaddingRight(5);
                table.addCell(cell1);

            }



            PdfWriter.getInstance(document, out).setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            document.open();
            Chunk vchunk=new Chunk(new VerticalPositionMark());

            Image image=Image.getInstance("D:\\java.jpg");
            image.scaleToFit(30,40);
            image.setAlignment(Image.ALIGN_RIGHT);


            document.add(tHeader);
            document.add(image);
            document.add(vchunk);
            document.add(tTitle);
            document.add(tDate);
            document.add(lineP);
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
