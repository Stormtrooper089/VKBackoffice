package com.vk.backoffice.qr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.backoffice.qr.entity.QrMeta;

public interface GeneratedQrMetaInfoRepo extends JpaRepository<QrMeta,Long>{
	
}
