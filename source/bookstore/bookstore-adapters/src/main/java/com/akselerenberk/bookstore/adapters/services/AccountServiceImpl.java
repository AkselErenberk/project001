package com.akselerenberk.bookstore.adapters.services;

import com.akselerenberk.bookstore.common.exception.DuplicateException;
import com.akselerenberk.bookstore.database.entities.AccountEntity;
import com.akselerenberk.bookstore.database.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "userService")
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String usernameValue) {
        Optional<AccountEntity> user = getAccountByUsername(usernameValue);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        detailsChecker.check(user.get());
        return user.get();
    }

    public Optional<AccountEntity> getAccountByUsername(String usernameValue) {
        return accountRepository.findByUsername(usernameValue);
    }

    @Override
    public String getCurrentUsername() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public Optional<AccountEntity> retrieveCurrentAccount() {
        val username = getCurrentUsername();
        return getAccountByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(AccountEntity account) {
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new DuplicateException("Username Already exist !!");
        }
        accountRepository.save(account);
    }

    /**
     * not implemented
     * @param user
     * @param newPassword
     * @return null
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}