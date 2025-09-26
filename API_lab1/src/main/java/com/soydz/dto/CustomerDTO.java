package com.soydz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerDTO(Long id, String accountNumber, String firstName, String lastName, BigDecimal balance) {
}
