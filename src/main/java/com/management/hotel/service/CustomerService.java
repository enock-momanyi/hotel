package com.management.hotel.service;

import com.management.hotel.model.Customer;
import com.management.hotel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, Map<String, Object> customerUpdate){
        return customerRepository.findById(id).map(customer1 -> {
            customerUpdate.forEach((key, value) -> {
                if(key.equals("email")){
                    customer1.setEmail((String) value);
                } else if (key.equals("firstName")) {
                    customer1.setFirstName((String) value);
                } else if (key.equals("lastName")) {
                    customer1.setLastName((String) value);
                } else if (key.equals("phoneNumber")) {
                    customer1.setPhoneNumber((String) value);
                }
            });
            return customerRepository.save(customer1);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
    public Optional<Customer> getCustomer(Long id){
        return customerRepository.findById(id);
    }
    public List<Customer> getAllcustomers(){
        return customerRepository.findAll();
    }
}
