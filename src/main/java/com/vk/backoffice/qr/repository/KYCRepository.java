package com.vk.backoffice.qr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.RVUserKyc;
import org.springframework.data.jpa.repository.Query;


public interface KYCRepository extends JpaRepository<RVUserKyc,Long>{
	@Query("select e from RVUserKyc e where e.mobileNumber = :mobileNumber")
	Optional<RVUserKyc> findByMobileNumber(String mobileNumber);
}
