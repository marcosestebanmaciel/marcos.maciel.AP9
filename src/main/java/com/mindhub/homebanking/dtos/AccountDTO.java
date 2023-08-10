package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate date;
    private Double balance;
    private Set<TransactionDTO> transactions = new HashSet<>();

    public AccountDTO(Account account){
        id = account.getId();
        number = account.getNumber();
        date = account.getDate();
        balance = account.getBalance();
        transactions = account.getTransactions().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getBalance() {
        return balance;
    }
}
