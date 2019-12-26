package com.vk.backoffice.qr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.RVUserKyc;


public interface KYCRepository extends JpaRepository<RVUserKyc,Long>{
	Optional<RVUserKyc> findByMobileNumber(String mobileNumber);
}
