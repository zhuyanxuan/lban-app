package com.app.lban.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/22 0022.
 */
public class Product {
    private Long productId;
    private Long sectionId;
    private String productName;
    private String productDetail;
    private String author;
    private String price;
    private int number;
    private Date departureDate;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String valid;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "{" +
                "productId:" + productId +
                ", sectionId:" + sectionId +
                ", productName:'" + productName + '\'' +
                ", productDetail:'" + productDetail + '\'' +
                ", author:'" + author + '\'' +
                ", price:'" + price + '\'' +
                ", number:" + number +
                ", departureDate:'" + departureDate + '\'' +
                ", createBy:'" + createBy + '\'' +
                ", createDate:'" + createDate + '\'' +
                ", updateBy:'" + updateBy + '\'' +
                ", updateDate:'" + updateDate + '\'' +
                ", valid:'" + valid + '\'' +
                '}';
    }
}
