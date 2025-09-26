package com.soydz.service;

import com.soydz.dto.CustomerDTO;
import com.soydz.entity.Customer;
import com.soydz.mapper.CustomerMapper;
import com.soydz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public  CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return customerMapper.toDTO(customerRepository.save(customerMapper.toEntity(customerDTO)));
    }


    public Customer findByAccountNumber(String accountNumber){
        return customerRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("The account " + accountNumber + " does not exist"));
    }
}
