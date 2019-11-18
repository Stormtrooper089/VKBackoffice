package com.rhovega.vankon.qr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("api")
public class QrController {

    @RequestMapping(path = "/generateQR",method = RequestMethod.GET)
    public void generateQRPdf(){
        String text = "98376373783"; // this is the text that we want to encode

        int width = 400;
        int height = 300; // change the height and width as per your requirement

// (ImageIO.getWriterFormatNames() returns a list of supported formats)
        String imageFormat = "png"; // could be "gif", "tiff", "jpeg"
try {
    BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
    MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, new FileOutputStream(new File("qrcode_97802017507991.png")));
}catch (Exception e){

}
    }
}
