package com.soydz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TransactionDTO(Long id, String senderAccountNumber, String receiverAccountNumber, BigDecimal amount) {
}
