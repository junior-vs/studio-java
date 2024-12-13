package tech.studio.classicmodel.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.PostMapping;
import tech.studio.classicmodel.model.Customer;

public record CustomerDto(Integer id,
                          @NotBlank String customerName,
                          @NotBlank  String contactLastName,
                          @NotBlank String contactFirstName,
                          @NotBlank String phone,
                          @NotBlank String addressLine1,
                          String addressLine2,
                          @NotBlank String city,
                          String state,
                          @NotBlank  String postalCode,
                          @NotBlank String country,
                          @NotNull @PositiveOrZero BigDecimal creditLimit) implements Serializable {

  public CustomerDto(Customer customer) {
    this(customer.getId(),
        customer.getCustomerName(),
        customer.getContactLastName(),
        customer.getContactFirstName(),
        customer.getPhone(),
        customer.getAddressLine1(),
        customer.getAddressLine2(),
        customer.getCity(),
        customer.getState(),
        customer.getPostalCode(),
        customer.getCountry(),
        customer.getCreditLimit());
  }

  public Customer map() {
    return new Customer(
        customerName,
        contactLastName,
        contactFirstName,
        phone,
        addressLine1,
        addressLine2,
        city,
        state,
        postalCode,
        country,
        creditLimit);
  }
}
