package com.soydz.mapper;

import com.soydz.dto.TransactionDTO;
import com.soydz.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO toDTO(Transaction transaction);
}
