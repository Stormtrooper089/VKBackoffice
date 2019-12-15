package com.vk.backoffice.qr.service;

import com.vk.backoffice.qr.entity.ProductMaster;
import com.vk.backoffice.qr.model.Product;
import com.vk.backoffice.qr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl {

    @Autowired
    ProductRepository productRepository;

    public String processProductMaster(List<Product> productList){
        if(productList!=null || productList.size() > 0){
            try {
                List<ProductMaster> productMasterList = new ArrayList<>();
                for (Product product : productList) {
                    ProductMaster master = new ProductMaster();
                    master.setItemCode(product.getItemCode());
                    master.setRange(product.getRange());
                    master.setProductName(product.getProductName());
                    productMasterList.add(master);
                }
                productRepository.saveAll(productMasterList);
            }catch (Exception e){
                return "failure";
            }
            return "success";
        }
        return "failure";
    }
}
