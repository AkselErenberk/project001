package com.akselerenberk.bookstore.core.services;

import com.akselerenberk.bookstore.core.models.Account;
import com.akselerenberk.bookstore.core.models.Credentials;
import com.akselerenberk.bookstore.core.models.Role;
import com.akselerenberk.bookstore.core.models.Token;
import com.akselerenberk.bookstore.core.models.VerificationLevel;
import com.akselerenberk.bookstore.core.ports.AccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountPort accountPort;

    public Token authenticateAccount(final Credentials credentials) {
        return accountPort.authenticateAccount(credentials);
    }

    public void registerNewAccount(final Credentials credentials) {
        accountPort.registerNewAccount(enrichNewUserAccount(credentials));
    }

    public Account retrieveAccount() {
        return accountPort.retrieveAccount();
    }

    private Account enrichNewUserAccount(final Credentials credentials) {
        return Account.builder()
                .password(credentials.password())
                .username(credentials.username())
                .roles(Set.of(Role.USER))
                .verificationLevel(VerificationLevel.LEVEL0)
                .build();
    }
}