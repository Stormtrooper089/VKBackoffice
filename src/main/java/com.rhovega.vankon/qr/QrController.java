package com.rhovega.vankon.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api")
public class QrController {



    @RequestMapping(path = "/generateQR", method = RequestMethod.GET)
    public String generateQRPdf() {
        int width = 200;
        int height = 200; // change the height and width as per your requirement

// (ImageIO.getWriterFormatNames() returns a list of supported formats)
        String imageFormat = "png"; // could be "gif", "tiff", "jpeg"
        Document document = new Document();
        try {

        PdfWriter.getInstance(document, new FileOutputStream("sample1.pdf"));
         document.open();
        for (int i = 1000; i < 10000; i = i + 100) {

                BitMatrix bitMatrix = new QRCodeWriter().encode(i+"", BarcodeFormat.QR_CODE, width, height);
                MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, new FileOutputStream(new File("qrcode_9821837934.png")));
                Image img = Image.getInstance("qrcode_9821837934.png");
                document.add(new Paragraph("QR COde Company "+ i));
                document.add(img);
                document.add(new Paragraph("COde "+ i));

        }
        } catch (Exception e) {
            System.out.print(e + "");
        }
        finally {
            document.close();
        }
        return "test";
    }


    @RequestMapping(path = "/generateQR6", method = RequestMethod.GET)
    public ResponseEntity generateQRPdfTable() {
        int width = 200;
        int height = 200; // change the height and width as per your requirement

// (ImageIO.getWriterFormatNames() returns a list of supported formats)
        String imageFormat = "png"; // could be "gif", "tiff", "jpeg"
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("sample3columpdf.pdf"));
            document.open();
            float[] columnWidths = {30,3,30,3,30,3};
            PdfPTable barCodeTable = new PdfPTable(6);
            barCodeTable.setWidthPercentage(100);
            barCodeTable.setWidths(columnWidths);


            for (int i = 1000; i < 10000; i = i + 1000) {

                for(int j=0;j<3;j++){
                    barCodeTable.addCell(newCell("COM"+j));
                    barCodeTable.addCell(blankCell());
                }
                for(int j=0;j<3;j++){
                    BitMatrix bitMatrix = new QRCodeWriter().encode(i+""+j, BarcodeFormat.QR_CODE, width, height);
                    MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, new FileOutputStream(new File("qrcode_9821837934.png")));
                    Image img = Image.getInstance("qrcode_9821837934.png");
                    barCodeTable.addCell(imageCell(img));
                    barCodeTable.addCell(blankCell());
                }
                for(int j=0;j<3;j++){
                    barCodeTable.addCell(newCell("*"+i+""+j+"*"));
                    barCodeTable.addCell(blankCell());
                }
                for(int j=0;j<3;j++){
                    barCodeTable.addCell(blankCell());
                    barCodeTable.addCell(blankCell());
                }
            }
            document.add(barCodeTable);
        } catch (Exception e) {
            System.out.print(e + "");
        }
        finally {
            document.close();
        }
        Path path = Paths.get("sample3columpdf.pdf");
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private PdfPCell newCell(String text){
        PdfPCell cell=new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        cell.setPhrase(new Paragraph(text));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell blankCell(){
        PdfPCell cell=new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        cell.setPhrase(new Paragraph(""));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell imageCell(Image img){
        PdfPCell cell=new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        cell.setImage(img);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(0);
        return cell;
    }
}
