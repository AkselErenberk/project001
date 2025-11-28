package com.akselerenberk.bookstore.adapters.services;

import com.akselerenberk.bookstore.database.entities.AccountEntity;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AccountService extends UserDetailsService, UserDetailsPasswordService {

    String getCurrentUsername();

    Optional<AccountEntity> getAccountByUsername(String username);

    Optional<AccountEntity> retrieveCurrentAccount();

    void create(AccountEntity account);
}