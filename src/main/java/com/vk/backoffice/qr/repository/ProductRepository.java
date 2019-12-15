package com.vk.backoffice.qr.repository;

import com.vk.backoffice.qr.entity.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductMaster,Long> {

}
