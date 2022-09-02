package tech.studio.classicmodel.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import org.hibernate.Hibernate;

@Embeddable
public class OrderdetailId implements Serializable {

  private static final long serialVersionUID = 1000805991021615534L;
  @Column(name = "orderNumber", nullable = false)
  private Integer orderNumber;

  @Column(name = "productCode", nullable = false, length = 15)
  private String productCode;

  public Integer getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    OrderdetailId entity = (OrderdetailId) o;
    return Objects.equals(this.orderNumber, entity.orderNumber) &&
        Objects.equals(this.productCode, entity.productCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderNumber, productCode);
  }

}