package com.rhovega.vankon.qr;


import java.math.BigInteger;
import java.util.Random;

public class QrCodeGenerator {
    static int [] primeNumberSet = {13,23,37,43,53,19,29,31,11,49};
    static int noOfQrCodesToGenerate = 100;
    public static void main(String args[]) {
        BigInteger startNumber= new BigInteger("111111111111");
        for(int i=0;i<noOfQrCodesToGenerate;i++) {
            BigInteger random = randomNumberGenerator();
            BigInteger numberToAdd = BigInteger.valueOf(primeNumberSet[random.intValue()]);
            startNumber=startNumber.add(numberToAdd);
        }
    }
    private static BigInteger randomNumberGenerator() {
        Random randomNumberGenerator = new Random();
        BigInteger randomnumber = BigInteger.valueOf(randomNumberGenerator.nextInt(10));
        return randomnumber;}

}
