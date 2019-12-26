package com.vk.backoffice.qr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.RVRedemptionRequest;
import com.vk.backoffice.qr.entity.RVUser;



public interface AccountRepository extends JpaRepository<RVUser, Long>{
	Optional<RVUser> findByMobileNumber(String mobileNumber);
	Optional<RVUser> save(Optional<RVUser> rvUserFound);
}
