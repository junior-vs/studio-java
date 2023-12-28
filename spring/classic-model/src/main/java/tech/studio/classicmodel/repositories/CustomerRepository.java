package tech.studio.classicmodel.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.studio.classicmodel.model.Customer;
import tech.studio.classicmodel.repositories.projections.CustomerInfo;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>,
    JpaSpecificationExecutor<Customer> {

  Optional<CustomerInfo> findCustomerInfoById(Integer id);
}