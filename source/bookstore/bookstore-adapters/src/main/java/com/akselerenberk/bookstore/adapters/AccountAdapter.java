package com.akselerenberk.bookstore.adapters;

import com.akselerenberk.bookstore.adapters.mappers.AccountMapper;
import com.akselerenberk.bookstore.adapters.services.AccountService;
import com.akselerenberk.bookstore.common.exception.DataNotFoundException;
import com.akselerenberk.bookstore.core.models.Account;
import com.akselerenberk.bookstore.core.models.Credentials;
import com.akselerenberk.bookstore.core.models.Token;
import com.akselerenberk.bookstore.core.ports.AccountPort;
import com.akselerenberk.bookstore.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAdapter implements AccountPort {

    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    @Override
    public Token authenticateAccount(final Credentials credentials) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        return new Token(token);
    }

    @Override
    public void registerNewAccount(Account account) {
        var encodedPassword = passwordEncoder.encode(account.password());

        val userEntity = AccountMapper.mapModelToEntity(account).toBuilder()
                .password(encodedPassword)
                .accountNonLocked(true)
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .build();

        accountService.create(userEntity);
    }

    @Override
    public Account retrieveAccount() {
        val userEntity = accountService.retrieveCurrentAccount().orElseThrow(DataNotFoundException::new);
        return AccountMapper.mapEntityToModel(userEntity);
    }

    @Override
    public Account retrieveAccount(final String username) {
        val userEntity = accountService.getAccountByUsername(username).orElseThrow(DataNotFoundException::new);
        return AccountMapper.mapEntityToModel(userEntity);
    }

    /**
     * not implemented
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
    }

}