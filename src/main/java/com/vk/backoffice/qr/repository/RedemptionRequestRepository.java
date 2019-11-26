package com.vk.backoffice.qr.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vk.backoffice.qr.entity.RVRedemptionRequest;



public interface RedemptionRequestRepository extends JpaRepository<RVRedemptionRequest, Long> {



}
