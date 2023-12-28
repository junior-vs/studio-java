package tech.studio.classicmodel.controllers;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.studio.classicmodel.dto.CustomerDto;
import tech.studio.classicmodel.model.Customer;
import tech.studio.classicmodel.repositories.CustomerRepository;

/**
 * Create [ok]
 * Read by id [ok]
 * Update
 * Delete
 * List with pagination and sort
 */


@RestController
@RequestMapping("/customers")
public class CustomersController {

@Autowired
  private CustomerRepository repository;

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> getByID(@PathVariable("id") Integer id){
    Optional<Customer> customerFound = repository.findById(id);
    return ResponseEntity.ok(new CustomerDto(customerFound.orElseThrow()));
  }

  @PostMapping
  public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerDto dto){
    Customer created = dto.map();
    repository.save(created);
    return ResponseEntity.ok(new CustomerDto(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDto> update(@PathVariable("id") Integer id, @RequestBody @Valid CustomerDto dto){
    Optional<Customer> customerFound = repository.findById(id);
    customerFound.orElseThrow().update(dto);
    repository.save(customerFound.get());

    return ResponseEntity.ok(new CustomerDto(customerFound.get()));
  }




}
