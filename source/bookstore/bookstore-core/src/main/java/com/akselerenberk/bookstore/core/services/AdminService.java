package com.akselerenberk.bookstore.core.services;

import com.akselerenberk.bookstore.core.models.Account;
import com.akselerenberk.bookstore.core.models.Role;
import com.akselerenberk.bookstore.core.models.VerificationLevel;
import com.akselerenberk.bookstore.core.ports.AccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

//TODO remove
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AccountPort accountPort;

    public void promoteToAdmin(final String username) {
        var account = accountPort.retrieveAccount(username);
        account.roles().add(Role.ADMIN);
        accountPort.updateAccount(account);
    }

    public void registerNewAccount(final Account account) {
        accountPort.registerNewAccount(enrichNewAdminAccount(account));
    }

    private Account enrichNewAdminAccount(final Account account) {
        return account.toBuilder()
                .roles(Set.of(Role.USER, Role.ADMIN))
                .verificationLevel(VerificationLevel.LEVEL1)
                .build();
    }
}
