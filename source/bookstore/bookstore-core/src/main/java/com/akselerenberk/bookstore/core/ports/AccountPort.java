package com.akselerenberk.bookstore.core.ports;

import com.akselerenberk.bookstore.core.models.Account;
import com.akselerenberk.bookstore.core.models.Credentials;
import com.akselerenberk.bookstore.core.models.Token;
import org.springframework.stereotype.Service;

@Service
public interface AccountPort {
    Token authenticateAccount(final Credentials credentials);

    void registerNewAccount(final Account account);

    Account retrieveAccount();

    Account retrieveAccount(final String username);

    void updateAccount(final Account account);
}