package com.rhovega.vankon.qr.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CodeGenerator {

    public int [] primeNumberSet = {13,23,37,43,53,19,29,31,11,49};

    public List<String> generateCodes(int noOfQrCodesToGenerate) {

        //Get batchId
        BigInteger batchId= new BigInteger("111111111111");
        List<String> generatedCodeList = new ArrayList<>();
        for(int i=0;i<noOfQrCodesToGenerate;i++) {
            BigInteger random = randomNumberGenerator();
            BigInteger numberToAdd = BigInteger.valueOf(primeNumberSet[random.intValue()]);
            batchId=batchId.add(numberToAdd);
            generatedCodeList.add(batchId.toString());
        }
        return generatedCodeList;
    }
    private static BigInteger randomNumberGenerator() {
        Random randomNumberGenerator = new Random();
        BigInteger randomnumber = BigInteger.valueOf(randomNumberGenerator.nextInt(10));
        return randomnumber;}
}
