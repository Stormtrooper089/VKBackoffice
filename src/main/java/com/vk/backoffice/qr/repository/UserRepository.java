package com.vk.backoffice.qr.repository;

import com.vk.backoffice.qr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
