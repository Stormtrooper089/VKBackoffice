package com.vk.backoffice.controller;

import com.vk.backoffice.qr.model.CreateQrRequest;
import com.vk.backoffice.qr.service.QrCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class QrController {

    @Autowired
    private QrCodeServiceImpl qrCodeService;

    @RequestMapping(path = "/generateQRExcel", method = RequestMethod.POST)
    public ResponseEntity printQrForExcel(@RequestBody CreateQrRequest qrRequest) {
        if(qrRequest != null) {
            Resource resource = qrCodeService.generateCodeByProductToExcel(qrRequest);
            if (resource != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
        }
        return null;
    }
}
