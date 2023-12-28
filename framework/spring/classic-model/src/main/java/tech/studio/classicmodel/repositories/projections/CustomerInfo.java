package tech.studio.classicmodel.repositories.projections;

public interface CustomerInfo {

  Integer getId();

  String getCustomerName();

  String getContactLastName();

  String getContactFirstName();

  String getPhone();

  String getAddressLine1();

  String getCity();

  String getState();

  String getPostalCode();

  String getCountry();
}
