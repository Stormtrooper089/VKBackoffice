package com.vk.backoffice.qr.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.QRGenerated;



public interface GeneratedQrCodeRepository extends JpaRepository<QRGenerated,Long>{

	
}
