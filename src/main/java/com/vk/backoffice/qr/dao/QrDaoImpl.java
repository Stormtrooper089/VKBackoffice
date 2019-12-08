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

    public List<Object[]> getGeneratedQrStatisticsByMonth(String queryType) {
        try {
            String queryString = new String();
            if (queryString.equals("qr")) {
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

}
