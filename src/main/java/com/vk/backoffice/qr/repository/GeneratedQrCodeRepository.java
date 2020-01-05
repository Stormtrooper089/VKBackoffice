package com.vk.backoffice.qr.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.QRGenerated;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface GeneratedQrCodeRepository extends JpaRepository<QRGenerated,Long>{

    @Modifying
    @Query("update QRGenerated e set e.isActivated = 'true' where e.batchId = :batchId")
    public void updateGeneratedQrCode(String batchId);
}
