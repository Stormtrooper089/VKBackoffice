package com.vk.backoffice.qr.repository;

import com.vk.backoffice.qr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
       @Query("select e from User e where e.mobileNumber = :mobileNumber")
       User findByUserMobileNumber(String mobileNumber);
}
