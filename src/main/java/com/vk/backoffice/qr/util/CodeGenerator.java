package com.vk.backoffice.qr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vk.backoffice.qr.entity.QRGenerated;
import com.vk.backoffice.qr.entity.QrMeta;
import com.vk.backoffice.qr.model.CreateQrRequest;
import com.vk.backoffice.qr.repository.GeneratedQrCodeRepository;
import com.vk.backoffice.qr.repository.GeneratedQrMetaInfoRepo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class CodeGenerator {
	
	@Autowired
	private GeneratedQrCodeRepository generatedQrCodeRepository;
	@Autowired
	private GeneratedQrMetaInfoRepo generatedQrMetaInfoRepo;

    public int [] primeNumberSet = {13,23,37,43,53,19,29,31,11,49};

    public List<String> generateCodes(CreateQrRequest crRequest) {

        //Get batchId
    	List<QRGenerated> listOfQrGenerated = new ArrayList<QRGenerated>();
    	
    	String batchIdString = getBatchID(); 
    	Date dateToday =  new Date();
        BigInteger batchId= new BigInteger("111111111112");
        List<String> generatedCodeList = new ArrayList<>();
        QrMeta qrMeta=new QrMeta();
        qrMeta.setActivationStatus(crRequest.getActivationStatus());
        qrMeta.setBatchId(batchId.toString());
        qrMeta.setNumberOfQrGenerated(crRequest.getNumberOfQrCodeRequired()+"");
        qrMeta.setPoints(crRequest.getPointsForCode()+"");
        qrMeta.setProductId(crRequest.getProductId());
        generatedQrMetaInfoRepo.save(qrMeta);
        
        for(int i=0;i<crRequest.getNumberOfQrCodeRequired();i++) {
            BigInteger random = randomNumberGenerator();
            BigInteger numberToAdd = BigInteger.valueOf(primeNumberSet[random.intValue()]);
            batchId = batchId.add(numberToAdd);
            generatedCodeList.add(batchId.toString());
        }
        
        for (String qrCode : generatedCodeList) {
			QRGenerated qrGenerated = new QRGenerated();
			
			qrGenerated.setBatchId(batchIdString);
			qrGenerated.setDateCreated(dateToday);
			qrGenerated.setIsActivated("Y");
			qrGenerated.setIsRedeemed("N");
			qrGenerated.setValue(String.valueOf(crRequest.getPointsForCode()));
			qrGenerated.setQrCode(qrCode);
			
			listOfQrGenerated.add(qrGenerated);
		}
        generatedQrCodeRepository.saveAll(listOfQrGenerated);
        return generatedCodeList;
    }
    private String getBatchID() {
		return "ASH";
	}
	private static BigInteger randomNumberGenerator() {
        Random randomNumberGenerator = new Random();
        BigInteger randomnumber = BigInteger.valueOf(randomNumberGenerator.nextInt(10));
        return randomnumber;}
}
