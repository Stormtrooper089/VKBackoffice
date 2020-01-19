package com.vk.backoffice.qr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class QrDaoImpl {

    @Autowired
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private String getQrStatQuery() {
        return "select MONTH(date_created) as date_created,count(id) " +
                "from qrgenerated " +
                "group by MONTH(date_created)";
    }

    private String getRedeemStatQuery() {
        return "select MONTH(redeem_date) as date_created,count(id) " +
                "                from rvqr_code " +
                "                group by MONTH(redeem_date)";
    }
    private String getTotalQrScannedQuery(){
        return "SELECT SUM(redeem_value) FROM rvqr_code" +
                " where STR_TO_DATE(redeem_date,'%Y-%m-%d') BETWEEN STR_TO_DATE(:fromDate,'%Y-%m-%d') AND STR_TO_DATE(:toDate,'%Y-%m-%d')";
    }

    private String getTotalRedeemQuery(){
        return "SELECT SUM(redeem_value) FROM rv_redemption" +
                " where STR_TO_DATE(redeem_date,'%Y-%m-%d') BETWEEN STR_TO_DATE(:fromDate,'%Y-%m-%d') AND STR_TO_DATE(:toDate,'%Y-%m-%d')";
    }

    private String getTop10ScannedProductsQuery(){
        return "SELECT qg.product_name,count(rc.id) FROM rvqr_code rc inner join qrgenerated qg on rc.qr_code= qg.qr_code " +
                " where STR_TO_DATE(redeem_date,'%Y-%m-%d') BETWEEN STR_TO_DATE(:fromDate,'%Y-%m-%d') AND STR_TO_DATE(:toDate,'%Y-%m-%d') " +
                " GROUP BY qg.product_name" +
                " order by count(rc.id) desc LIMIT 10";
    }
    private String getTotalUserQuery(){
        return "select active as 'key',COUNT(*) as 'value' from rv_user group by active order by active DESC";
    }
    public List<Object[]> getGeneratedQrStatisticsByMonth(String queryType) {
        try {
            String queryString = new String();
            if (queryType.equals("qr")) {
                queryString = getQrStatQuery();
            } else {
                queryString = getRedeemStatQuery();
            }
            Query query = entityManager.createNativeQuery(queryString);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public String getTotalQrScannedAmount(String fromDate, String toDate){
        try {
            Query query = entityManager.createNativeQuery(getTotalQrScannedQuery());
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            return query.getSingleResult().toString();
        }catch (Exception e){
            return null;
        }
    }

    public String getTotalRedeemAmount(String fromDate, String toDate){
        try {
            Query query = entityManager.createNativeQuery(getTotalRedeemQuery());
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            return query.getSingleResult().toString();
        }catch (Exception e){
            return null;
        }
    }
    public List<Object[]> getTop10ScannedProducts(String fromDate,String toDate){
        try {
            Query query = entityManager.createNativeQuery(getTop10ScannedProductsQuery());
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            return query.getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
