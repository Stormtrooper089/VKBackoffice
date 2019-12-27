package com.vk.backoffice.qr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vk.backoffice.qr.entity.LastQrNumber;
import com.vk.backoffice.qr.entity.QRGenerated;
import com.vk.backoffice.qr.entity.QrMeta;
import com.vk.backoffice.qr.model.CreateQrRequest;
import com.vk.backoffice.qr.repository.GeneratedQrCodeRepository;
import com.vk.backoffice.qr.repository.GeneratedQrMetaInfoRepo;
import com.vk.backoffice.qr.repository.LastQrCodeRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class CodeGenerator {
	
	@Autowired
	private GeneratedQrCodeRepository generatedQrCodeRepository;
	@Autowired
	private GeneratedQrMetaInfoRepo generatedQrMetaInfoRepo;

	@Autowired
	private LastQrCodeRepository generatedLastQrNumberInfo;
	
    public int [] primeNumberSet = {13,23,37,43,53,19,29,31,11,49};

    public List<String> generateCodes(CreateQrRequest crRequest) {

        //Get batchId
    	List<QRGenerated> listOfQrGenerated = new ArrayList<QRGenerated>();
    	
    	String batchIdString = getBatchID(); 
    	Date dateToday =  new Date();
    	Optional<LastQrNumber> lastQrNumber = generatedLastQrNumberInfo.findById((long) 1);
        BigInteger batchToStartFrom= new BigInteger(lastQrNumber.get().getLastNumberGenerated());
        
        List<String> generatedCodeList = new ArrayList<>();
        populateMEtaData(crRequest, batchIdString);
        
        for(int i=0;i<crRequest.getNumberOfQrCodeRequired();i++) {
            BigInteger random = randomNumberGenerator();
            BigInteger numberToAdd = BigInteger.valueOf(primeNumberSet[random.intValue()]);
            batchToStartFrom = batchToStartFrom.add(numberToAdd);
            generatedCodeList.add(batchToStartFrom.toString());
        }
        
        for (String qrCode : generatedCodeList) {
			QRGenerated qrGenerated = new QRGenerated();
			
			qrGenerated.setBatchId(batchIdString);
			qrGenerated.setDateCreated(dateToday);
			qrGenerated.setIsActivated(crRequest.getActivationStatus());
			qrGenerated.setIsRedeemed("N");
			qrGenerated.setValue(String.valueOf(crRequest.getPointsForCode()));
			qrGenerated.setQrCode(qrCode);
			qrGenerated.setProductName(crRequest.getProductId());
			
			listOfQrGenerated.add(qrGenerated);
		}
        if(lastQrNumber.isPresent()) {
        	int size = generatedCodeList.size();	
        	lastQrNumber.get().setLastNumberGenerated(generatedCodeList.get(size-1));
        	generatedLastQrNumberInfo.save(lastQrNumber.get());
        }
        
        generatedQrCodeRepository.saveAll(listOfQrGenerated);
        return generatedCodeList;
    }
    private String getBatchID() {
    	
		return String.valueOf(TimeUnit.MILLISECONDS);
	}
	private static BigInteger randomNumberGenerator() {
        Random randomNumberGenerator = new Random();
        BigInteger randomnumber = BigInteger.valueOf(randomNumberGenerator.nextInt(10));
        return randomnumber;
    }
	private void populateMEtaData(CreateQrRequest crRequest,String batchIdString ) {
		QrMeta qrMeta=new QrMeta();
        System.out.println("activation status"+crRequest.getActivationStatus());
        qrMeta.setActivationStatus(crRequest.getActivationStatus().equalsIgnoreCase("true") ? 1 : 0);
        System.out.println("activation status after "+qrMeta.getActivationStatus());
        qrMeta.setBatchId(batchIdString);
        qrMeta.setNumberOfQrGenerated(crRequest.getNumberOfQrCodeRequired()+"");
        qrMeta.setPoints(crRequest.getPointsForCode()+"");
        qrMeta.setProductId(crRequest.getProductId());
        generatedQrMetaInfoRepo.save(qrMeta);
	}
}
