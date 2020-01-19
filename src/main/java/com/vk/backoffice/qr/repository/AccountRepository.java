package com.vk.backoffice.qr.repository;

import java.util.List;
import java.util.Optional;

import com.vk.backoffice.qr.model.Statistic;
import com.vk.backoffice.qr.model.UserStat;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.RVRedemptionRequest;
import com.vk.backoffice.qr.entity.RVUser;
import org.springframework.data.jpa.repository.Query;


public interface AccountRepository extends JpaRepository<RVUser, Long>{
	Optional<RVUser> findByMobileNumber(String mobileNumber);
	Optional<RVUser> save(Optional<RVUser> rvUserFound);

	@Query("select new com.vk.backoffice.qr.model.UserStat( e.isActive, count(e)) from RVUser e group by e.isActive")
	List<UserStat> getUserCountByActiveStatus();
}
