package tech.studio.classicmodel.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class Orderdetail {

  @EmbeddedId
  private OrderdetailId id;

  @MapsId("orderNumber")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "orderNumber", nullable = false)
  private Order orderNumber;

  @MapsId("productCode")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "productCode", nullable = false)
  private Product productCode;

  @Column(name = "quantityOrdered", nullable = false)
  private Integer quantityOrdered;

  @Column(name = "priceEach", nullable = false, precision = 10, scale = 2)
  private BigDecimal priceEach;

  @Column(name = "orderLineNumber", nullable = false)
  private Integer orderLineNumber;

  public OrderdetailId getId() {
    return id;
  }

  public void setId(OrderdetailId id) {
    this.id = id;
  }

  public Order getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Order orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Product getProductCode() {
    return productCode;
  }

  public void setProductCode(Product productCode) {
    this.productCode = productCode;
  }

  public Integer getQuantityOrdered() {
    return quantityOrdered;
  }

  public void setQuantityOrdered(Integer quantityOrdered) {
    this.quantityOrdered = quantityOrdered;
  }

  public BigDecimal getPriceEach() {
    return priceEach;
  }

  public void setPriceEach(BigDecimal priceEach) {
    this.priceEach = priceEach;
  }

  public Integer getOrderLineNumber() {
    return orderLineNumber;
  }

  public void setOrderLineNumber(Integer orderLineNumber) {
    this.orderLineNumber = orderLineNumber;
  }

}