package com.vk.backoffice.qr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
@Component
public class EmailUtil {
    @Autowired
    JavaMailSender javaMailSender;
    public boolean sendEmailWithAttachment(String qrScanned,String qrRedeem, List<Object[]> objects){

        try {
        MimeMessage message = javaMailSender.createMimeMessage();
        // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(InternetAddress.parse("sudarshanshinde1091@gmail.com,ashishunhale.1420@gmail.com,rkgi10@gmail.com"));
            helper.setSubject("Daily Summary Vankon");
            helper.setText(generateHtmlBody(qrScanned,qrRedeem,objects).toString(),true);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }

    private StringBuilder generateHtmlBody(String totalqrScanned, String totalPointsRedeemed, List<Object[]> topProducts){
        StringBuilder builder = new StringBuilder();
        builder.append("<table border='1' style='width:100%;'>");
        builder.append("<tr>");
        builder.append("<td  style='width:50%;'>");
        builder.append("Total QR Scanned "+ totalqrScanned);
        builder.append("</td>");
        builder.append("<td  style='width:50%;'>");
        builder.append("Total Redeemed "+totalPointsRedeemed);
        builder.append("</td>");
        builder.append("</tr>");
        builder.append("</table>");

        if(topProducts.size() > 0){
            builder.append("<h3>Top Products of the Day</h3>");
            builder.append("<table border='1' style='width:100%;'>");
            builder.append("<tr>");
            builder.append("<th>");
            builder.append("Product Name");
            builder.append("</th>");
            builder.append("<th>");
            builder.append("QR Count");
            builder.append("</th>");
            builder.append("</tr>");

        for(Object[] product: topProducts) {

            builder.append("<tr>");
            builder.append("<td>");
            builder.append(""+product[0]);
            builder.append("</td>");
            builder.append("<td>");
            builder.append(""+product[1]);
            builder.append("</td>");
            builder.append("</tr>");
        }

        builder.append("</table>");
        }else
        {
            builder.append("<h3>QR Code has not been scanned for any product</h3>");
        }

        return builder;
    }
}
