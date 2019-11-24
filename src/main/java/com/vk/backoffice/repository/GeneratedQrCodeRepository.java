package com.vk.backoffice.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.QRGenerated;



public interface GeneratedQrCodeRepository extends JpaRepository<QRGenerated,Long>{

	
}
