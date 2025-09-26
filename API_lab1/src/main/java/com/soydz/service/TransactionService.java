package com.soydz.service;

import com.soydz.dto.TransactionDTO;
import com.soydz.entity.Customer;
import com.soydz.entity.Transaction;
import com.soydz.mapper.CustomerMapper;
import com.soydz.mapper.TransactionMapper;
import com.soydz.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final TransactionMapper transactionMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CustomerService customerService, TransactionMapper transactionMapper, CustomerMapper customerMapper) {
        this.transactionRepository = transactionRepository;
        this.customerService = customerService;
        this.transactionMapper = transactionMapper;
        this.customerMapper = customerMapper;
    }

    public TransactionDTO transferMoney(TransactionDTO transactionDTO) {
        if (transactionDTO.senderAccountNumber() == null ||
                transactionDTO.senderAccountNumber().isEmpty() ||
                transactionDTO.receiverAccountNumber() == null ||
                transactionDTO.receiverAccountNumber().isEmpty()) {

            throw new IllegalArgumentException("Account numbers are required.");
        }

        Customer sender = customerService.findByAccountNumber(transactionDTO.senderAccountNumber());
        Customer receiver = customerService.findByAccountNumber(transactionDTO.receiverAccountNumber());

        if (sender.getBalance().compareTo(transactionDTO.amount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the sender's account: " + sender.getAccountNumber());
        }

        sender.setBalance(sender.getBalance().subtract(transactionDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.amount()));

        customerService.createCustomer(customerMapper.toDTO(sender));
        customerService.createCustomer(customerMapper.toDTO(receiver));

        return transactionMapper.toDTO(
                transactionRepository.save(
                        Transaction.builder()
                                .senderAccountNumber(sender.getAccountNumber())
                                .receiverAccountNumber(receiver.getAccountNumber())
                                .amount(transactionDTO.amount())
                                .build()
                )
        );
    }

    public List<TransactionDTO> getTransactionsForAccount(String accountNumber) {
        return transactionRepository.findBySenderAccountNumberOrReceiverAccountNumber(accountNumber, accountNumber).stream()
                .map(transactionMapper::toDTO)
                .toList();
    }

    public TransactionDTO getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Transaction number " + id + " does not exist"));
    }

    public List<TransactionDTO> getAllTransaction() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toDTO)
                .toList();
    }
}
