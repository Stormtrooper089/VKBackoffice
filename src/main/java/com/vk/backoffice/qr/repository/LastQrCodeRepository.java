package com.vk.backoffice.qr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.LastQrNumber;


public interface LastQrCodeRepository extends JpaRepository<LastQrNumber, Long> {

	
	
}
