package tech.studio.classicmodel.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import tech.studio.classicmodel.dto.CustomerDto;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customerNumber", nullable = false)
  private Integer id;

  @Column(name = "customerName", nullable = false, length = 50)
  private String customerName;

  @Column(name = "contactLastName", nullable = false, length = 50)
  private String contactLastName;

  @Column(name = "contactFirstName", nullable = false, length = 50)
  private String contactFirstName;

  @Column(name = "phone", nullable = false, length = 50)
  private String phone;

  @Column(name = "addressLine1", nullable = false, length = 50)
  private String addressLine1;

  @Column(name = "addressLine2", length = 50)
  private String addressLine2;

  @Column(name = "city", nullable = false, length = 50)
  private String city;

  @Column(name = "state", length = 50)
  private String state;

  @Column(name = "postalCode", length = 15)
  private String postalCode;

  @Column(name = "country", nullable = false, length = 50)
  private String country;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "salesRepEmployeeNumber")
  private Employee salesRepEmployeeNumber;

  @Column(name = "creditLimit", precision = 10, scale = 2)
  private BigDecimal creditLimit;

  public Customer( String customerName, String contactLastName, String contactFirstName,
      String phone, String addressLine1, String addressLine2, String city, String state,
      String postalCode, String country, BigDecimal creditLimit) {

    this.customerName = customerName;
    this.contactLastName = contactLastName;
    this.contactFirstName = contactFirstName;
    this.phone = phone;
    this.addressLine1 = addressLine1;
    this.addressLine2 = addressLine2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
    this.creditLimit = creditLimit;
  }

  public Customer() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getContactLastName() {
    return contactLastName;
  }

  public void setContactLastName(String contactLastName) {
    this.contactLastName = contactLastName;
  }

  public String getContactFirstName() {
    return contactFirstName;
  }

  public void setContactFirstName(String contactFirstName) {
    this.contactFirstName = contactFirstName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Employee getSalesRepEmployeeNumber() {
    return salesRepEmployeeNumber;
  }

  public void setSalesRepEmployeeNumber(Employee salesRepEmployeeNumber) {
    this.salesRepEmployeeNumber = salesRepEmployeeNumber;
  }

  public BigDecimal getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(BigDecimal creditLimit) {
    this.creditLimit = creditLimit;
  }

  public void update(CustomerDto dto) {
    this.customerName = dto.customerName();
    this.contactLastName =  dto.contactLastName();
    this.contactFirstName =  dto.contactFirstName();
    this.phone =  dto.phone();
    this.addressLine1 =  dto.addressLine1();
    this.addressLine2 =  dto.addressLine2();
    this.city =  dto.city();
    this.state =  dto.state();
    this.postalCode =  dto.postalCode();
    this.country =  dto.country();
    this.creditLimit =  dto.creditLimit();
  }
}