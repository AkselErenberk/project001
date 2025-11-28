package com.akselerenberk.bookstore.core.services;

import com.akselerenberk.bookstore.core.models.Account;
import com.akselerenberk.bookstore.core.models.Credentials;
import com.akselerenberk.bookstore.core.models.Role;
import com.akselerenberk.bookstore.core.models.Token;
import com.akselerenberk.bookstore.core.models.VerificationLevel;
import com.akselerenberk.bookstore.core.ports.AccountPort;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountPort accountPort;
    @InjectMocks
    private AccountService accountService;

    @Test
    void should_authenticateAccount() {
        // GIVEN
        val credentials = Credentials.builder().username("theUsername").password("thePassword").build();
        val token = new Token("theToken");
        when(accountPort.authenticateAccount(credentials)).thenReturn(token);

        // WHEN
        val result = accountService.authenticateAccount(credentials);

        // THEN
        assertThat(result).isEqualTo(token);
        verify(accountPort, times(1)).authenticateAccount(credentials);
    }

    @Test
    void should_registerNewAccount() {
        // GIVEN
        val credentials = Credentials.builder()
                .password("password")
                .username("username")
                .build();
        val account = Account.builder()
                .password("password")
                .username("username")
                .roles(Set.of(Role.USER))
                .verificationLevel(VerificationLevel.LEVEL0)
                .build();

        // WHEN
        accountService.registerNewAccount(credentials);

        // THEN
        verify(accountPort, times(1)).registerNewAccount(eq(account));
    }

    @Test
    void should_retrieveAccount() {
        // GIVEN
        val account = Account.builder().build();
        when(accountPort.retrieveAccount()).thenReturn(account);

        // WHEN
        val actual = accountService.retrieveAccount();

        // THEN
        assertThat(actual).isEqualTo(account);
        verify(accountPort, times(1)).retrieveAccount();
    }
}