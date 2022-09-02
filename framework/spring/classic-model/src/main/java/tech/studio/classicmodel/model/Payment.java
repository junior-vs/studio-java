package tech.studio.classicmodel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

  @EmbeddedId
  private PaymentId id;

  @MapsId("customerNumber")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customerNumber", nullable = false)
  private Customer customerNumber;

  @Column(name = "paymentDate", nullable = false)
  private LocalDate paymentDate;

  @Column(name = "amount", nullable = false, precision = 10, scale = 2)
  private BigDecimal amount;

  public PaymentId getId() {
    return id;
  }

  public void setId(PaymentId id) {
    this.id = id;
  }

  public Customer getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(Customer customerNumber) {
    this.customerNumber = customerNumber;
  }

  public LocalDate getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

}