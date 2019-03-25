package com.example.demo.service;

import com.example.demo.exception.AccountTransactionException;
import com.example.demo.model.Account;
import com.example.demo.model.Transfer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {
    private static final Double INITIAL_PAYMENT = 50.0;
    private static final String ACCOUNT_NOT_FOUND = "Account not found ";
    private static final String NOT_ENOUGH_MONEY = "Not enough money in the account ";
    private AccountRepository accountRepository;
    private TransferRepository transferRepository;

    public AccountService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    public Boolean addAccount(String name, Double amount) {
        if (amount < INITIAL_PAYMENT) {
            return false;
        }
        accountRepository.save(new Account(name, amount));
        return true;
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccountById(Integer id) {
        return accountRepository.findAccountById(id);
    }

    public void saveTransfer(Transfer transfer) {
        transferRepository.save(transfer);
    }

    public List<Transfer> findAllTransfers() {
        return transferRepository.findAll();
    }

    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Integer id, Double amount) throws AccountTransactionException {
        Account account = accountRepository.findAccountById(id);
        if(account == null) {
            throw new AccountTransactionException(ACCOUNT_NOT_FOUND + id);
        }
        double newBalance = account.getBalance() + amount;
        if(newBalance < 0.0) {
            throw new AccountTransactionException(NOT_ENOUGH_MONEY + id);
        }
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = AccountTransactionException.class)
    public void transfer(Integer fromAccountId, Integer toAccountId, Double amount) throws AccountTransactionException {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
        transferRepository.save(new Transfer(fromAccountId, toAccountId, amount));
    }
}
