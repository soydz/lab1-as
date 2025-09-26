package com.soydz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sender_account_number", nullable = false)
    private String senderAccountNumber;
    @Column(name = "receiver_account_number", nullable = false)
    private String receiverAccountNumber;
    @Column(nullable = false)
    private BigDecimal amount;
}
