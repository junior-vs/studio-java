package tech.studio.classicmodel.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @Column(name = "productCode", nullable = false, length = 15)
  private String id;

  @Column(name = "productName", nullable = false, length = 70)
  private String productName;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "productLine", nullable = false)
  private Productline productLine;

  @Column(name = "productScale", nullable = false, length = 10)
  private String productScale;

  @Column(name = "productVendor", nullable = false, length = 50)
  private String productVendor;

  @Lob
  @Column(name = "productDescription", nullable = false)
  private String productDescription;

  @Column(name = "quantityInStock", nullable = false)
  private Integer quantityInStock;

  @Column(name = "buyPrice", nullable = false, precision = 10, scale = 2)
  private BigDecimal buyPrice;

  @Column(name = "MSRP", nullable = false, precision = 10, scale = 2)
  private BigDecimal msrp;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Productline getProductLine() {
    return productLine;
  }

  public void setProductLine(Productline productLine) {
    this.productLine = productLine;
  }

  public String getProductScale() {
    return productScale;
  }

  public void setProductScale(String productScale) {
    this.productScale = productScale;
  }

  public String getProductVendor() {
    return productVendor;
  }

  public void setProductVendor(String productVendor) {
    this.productVendor = productVendor;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public Integer getQuantityInStock() {
    return quantityInStock;
  }

  public void setQuantityInStock(Integer quantityInStock) {
    this.quantityInStock = quantityInStock;
  }

  public BigDecimal getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(BigDecimal buyPrice) {
    this.buyPrice = buyPrice;
  }

  public BigDecimal getMsrp() {
    return msrp;
  }

  public void setMsrp(BigDecimal msrp) {
    this.msrp = msrp;
  }

}