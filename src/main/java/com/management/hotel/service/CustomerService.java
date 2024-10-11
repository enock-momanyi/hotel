package com.management.hotel.service;

import com.management.hotel.model.Customer;
import com.management.hotel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, Customer customer){
        return customerRepository.findById(id).map(customer1 -> {
            customer1.setEmail(customer.getEmail());
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setPhoneNumber(customer.getPhoneNumber());
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
