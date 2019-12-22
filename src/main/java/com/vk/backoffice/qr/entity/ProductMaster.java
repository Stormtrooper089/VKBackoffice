package com.vk.backoffice.qr.entity;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class ProductMaster {
    @Id
    @Column(name = "productid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "itemcode")
    private String itemCode;
    @Column(name = "itemrange")
    private String itemrange;
    @Column(name = "productname")
    private String productName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemrange() {
        return itemrange;
    }

    public void setItemrange(String itemrange) {
        this.itemrange = itemrange;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
