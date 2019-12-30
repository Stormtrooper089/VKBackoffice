package com.vk.backoffice.qr.repository;

import com.vk.backoffice.qr.entity.NotificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRequestRepository extends JpaRepository<NotificationRequest,Long> {
}
