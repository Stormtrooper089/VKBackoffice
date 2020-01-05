package com.vk.backoffice.qr.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.vk.backoffice.qr.entity.QrMeta;
import com.vk.backoffice.qr.model.CreateQrRequest;
import com.vk.backoffice.qr.model.QrMaster;
import com.vk.backoffice.qr.repository.GeneratedQrCodeRepository;
import com.vk.backoffice.qr.repository.GeneratedQrMetaInfoRepo;
import com.vk.backoffice.qr.util.CodeGenerator;
import com.vk.backoffice.qr.util.RequestStatusResponse;
import com.vk.backoffice.qr.util.VankonConstant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QrCodeServiceImpl {


    @Autowired
    private CodeGenerator codeGenerator;

    private final String IMAGE_FORMAT = "png";
    private final int QR_WIDTH = 200;
    private final int QR_HEIGHT = 200;

    @Autowired
    @Lazy
    private GeneratedQrMetaInfoRepo generatedQrMetaInfoRepo;

    @Autowired
    private GeneratedQrCodeRepository generatedQrCodeRepository;

    public Resource generateCodeByProductToExcel(CreateQrRequest qrRequest) {

        if (qrRequest.getProductId() != null) {
            String productId = qrRequest.getProductId();
            String qrCodeImageFileName = "qrcode_" + productId + "." + IMAGE_FORMAT;
            String outputExcelFileName = "Excel" + productId + ".xlsx";

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(productId);

            try {

                List<String> generatedCodeList = codeGenerator.generateCodes(qrRequest);
                int excelRow = 1;
                int codeIndex = 0;
                while (codeIndex < generatedCodeList.size()) {
                    Row firstRow = sheet.createRow(excelRow++);
                    int middleRow = excelRow++;
                    Row thirdRow = sheet.createRow(excelRow++);
                    int prevCodeIndex = codeIndex;
                    for (int column = 1; column < 4; column++) {
                        codeIndex = prevCodeIndex + (column-1);
                        if (codeIndex < generatedCodeList.size()) {

                            //GENERATE PRODUCT NAME ROW
                            Cell topCell = firstRow.createCell(column);
                            topCell.setCellValue(productId);

                            generateQrCodeImage(generatedCodeList.get(codeIndex), qrCodeImageFileName);
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
                            anchor.setRow1(middleRow);

                            //DRAW PICTURE
                            Picture pict = drawing.createPicture(anchor, pictureIds);
                            pict.resize();


                            //GENERATE QR CODE NAME ROW
                            Cell bottomCell = thirdRow.createCell(column);
                            bottomCell.setCellValue("* " + generatedCodeList.get(codeIndex) + " *");
                        }
                    }
                    codeIndex++;
                }


                FileOutputStream fileOut = new FileOutputStream(outputExcelFileName);
                workbook.write(fileOut);
                fileOut.close();

                return createResource(outputExcelFileName);
            } catch (IOException e) {
                System.out.print(e + "");
                return null;
            } catch (Exception e) {
                System.out.print(e + "");
                return null;
            } finally {
                //document.close();
            }
        }
        return null;
    }

    private void generateQrCodeImage(String generatedCode, String qrCodeImageFileName) {
        //GENERATE QR CODE
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(generatedCode, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
            MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_FORMAT, new FileOutputStream(new File(qrCodeImageFileName)));
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Resource createResource(String outputFileName) {
        try {
            Path path = Paths.get(outputFileName);
            Resource resource = new UrlResource(path.toUri());
            return resource;
        } catch (MalformedURLException e) {
            System.out.print(e + "");
            return null;
        }
    }

    public List<QrMaster> getQrMasterInformation() {
        List<QrMaster> qrMasters = new ArrayList<>();
        List<QrMeta> qrMetaList = generatedQrMetaInfoRepo.findAll();
        for (QrMeta qrMeta : qrMetaList) {
            QrMaster qrMaster = new QrMaster();
            qrMaster.setId(qrMeta.getId());
            qrMaster.setBatchId(qrMeta.getBatchId());
            qrMaster.setPoints(qrMeta.getPoints());
            qrMaster.setActivationStatus(qrMeta.getActivationStatus());
            qrMaster.setNumberOfQrGenerated(qrMeta.getNumberOfQrGenerated());
            qrMaster.setCreatedBy(qrMeta.getCreatedBy());
            qrMaster.setCreationDate(qrMeta.getCreationDate());
            qrMaster.setModifiedDate(qrMeta.getModifiedDate());
            qrMaster.setModifiedBy(qrMeta.getModifiedBy());
            qrMaster.setProductId(qrMeta.getProductId());
            qrMasters.add(qrMaster);
        }
        return qrMasters;
    }

    public RequestStatusResponse activateQrBatch(QrMaster qrMaster){
       RequestStatusResponse response = new RequestStatusResponse();
        try {
           Optional<QrMeta> qrMeta = generatedQrMetaInfoRepo.findById(qrMaster.getId());
           if (qrMeta.isPresent()) {
               qrMeta.get().setActivationStatus(1);
               generatedQrMetaInfoRepo.save(qrMeta.get());
               generatedQrCodeRepository.updateGeneratedQrCode(qrMaster.getBatchId());
               response.setResponseStatus(VankonConstant.SUCCESS);
               response.setResponseStatusDescription("QR batch is now activated.");
           }else{
               response.setResponseStatus(VankonConstant.FAILURE);
               response.setResponseStatusDescription("Qr batch does not exist. Please contact Tech Support.");
           }
       }
       catch (Exception ex){
           response.setResponseStatus(VankonConstant.FAILURE);
           response.setResponseStatusDescription("Qr batch does not exist. Please contact Tech Support.");
           ex.printStackTrace();
       }
        return response;
    }
}
