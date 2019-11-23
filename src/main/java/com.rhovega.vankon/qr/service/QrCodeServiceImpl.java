package com.rhovega.vankon.qr.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.rhovega.vankon.qr.model.CreateQrRequest;
import com.rhovega.vankon.qr.util.CodeGenerator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class QrCodeServiceImpl {


    @Autowired
    private CodeGenerator codeGenerator;

    private final String IMAGE_FORMAT="png";
    private final int QR_WIDTH = 200;
    private final int QR_HEIGHT = 200;

    public Resource generateCodeByProductToExcel(CreateQrRequest qrRequest){

        if(qrRequest != null) {
            if(qrRequest.getProductId() != null) {
                String productId= qrRequest.getProductId();
                String qrCodeImageFileName= "qrcode_"+productId+"."+IMAGE_FORMAT;
                String outputExcelFileName= "Excel"+productId+".xlsx";

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet(productId);

                try {
                    int excelRow = 0;
                    List<String> generatedCodeList = codeGenerator.generateCodes(100);
                    for (String generatedCode : generatedCodeList) {

                        excelRow++;
                        Row newRow = sheet.createRow(excelRow);
                        for (int column = 0; column < 3; column++) {
                            //GENERATE PRODUCT NAME ROW
                            Cell newCell = newRow.createCell(column);
                            newCell.setCellValue(productId);
                        }

                        excelRow++;
                        for (int column = 0; column < 3; column++) {

                           generateQrCodeImage(generatedCode,qrCodeImageFileName);

                            //READ IMAGE
                            InputStream inputStream = new FileInputStream(qrCodeImageFileName);
                            byte[] imageBytes = IOUtils.toByteArray(inputStream);
                            int pictureIds = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
                            inputStream.close();

                            //INITIALIZE EXCEL FOR PICTURE
                            CreationHelper helper = workbook.getCreationHelper();
                            Drawing drawing = sheet.createDrawingPatriarch();
                            ClientAnchor anchor = helper.createClientAnchor();
                            anchor.setCol1(column);
                            anchor.setRow1(excelRow);

                            //DRAW PICTURE
                            Picture pict = drawing.createPicture(anchor, pictureIds);
                            pict.resize();

                        }
                        excelRow++;
                        newRow = sheet.createRow(excelRow);
                        for (int column = 0; column < 3; column++) {
                            //GENERATE QR CODE NAME ROW
                            Cell newCell = newRow.createCell(column);
                            newCell.setCellValue("* " + generatedCode + " *");
                        }
                    }


                    FileOutputStream fileOut = new FileOutputStream(outputExcelFileName);
                    workbook.write(fileOut);
                    fileOut.close();

                    return createResource(outputExcelFileName);
                }catch (IOException e) {
                    System.out.print(e + "");
                    return null;
                }catch (Exception e) {
                    System.out.print(e + "");
                    return null;
                } finally {
                    //document.close();
                }
            }
        }
        return null;
    }

    private void generateQrCodeImage(String generatedCode,String qrCodeImageFileName){
        //GENERATE QR CODE
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(generatedCode, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
            MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_FORMAT, new FileOutputStream(new File(qrCodeImageFileName)));
        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Resource createResource(String outputFileName){
        try {
            Path path = Paths.get(outputFileName);
            Resource resource = new UrlResource(path.toUri());
            return resource;
        }catch (MalformedURLException e) {
            System.out.print(e + "");
            return null;
        }
    }
}
