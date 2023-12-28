package tech.studio.classicmodel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offices")
public class Office {

  @Id
  @Column(name = "officeCode", nullable = false, length = 10)
  private String id;

  @Column(name = "city", nullable = false, length = 50)
  private String city;

  @Column(name = "phone", nullable = false, length = 50)
  private String phone;

  @Column(name = "addressLine1", nullable = false, length = 50)
  private String addressLine1;

  @Column(name = "addressLine2", length = 50)
  private String addressLine2;

  @Column(name = "state", length = 50)
  private String state;

  @Column(name = "country", nullable = false, length = 50)
  private String country;

  @Column(name = "postalCode", nullable = false, length = 15)
  private String postalCode;

  @Column(name = "territory", nullable = false, length = 10)
  private String territory;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getTerritory() {
    return territory;
  }

  public void setTerritory(String territory) {
    this.territory = territory;
  }

}