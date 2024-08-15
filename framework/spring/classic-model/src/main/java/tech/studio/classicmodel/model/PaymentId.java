package tech.studio.classicmodel.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import org.hibernate.Hibernate;

@Embeddable
public class PaymentId implements Serializable {

  private static final long serialVersionUID = 7692604481553424741L;
  @Column(name = "customerNumber", nullable = false)
  private Integer customerNumber;

  @Column(name = "checkNumber", nullable = false, length = 50)
  private String checkNumber;

  public Integer getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(Integer customerNumber) {
    this.customerNumber = customerNumber;
  }

  public String getCheckNumber() {
    return checkNumber;
  }

  public void setCheckNumber(String checkNumber) {
    this.checkNumber = checkNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    PaymentId entity = (PaymentId) o;
    return Objects.equals(this.checkNumber, entity.checkNumber) &&
        Objects.equals(this.customerNumber, entity.customerNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(checkNumber, customerNumber);
  }

}