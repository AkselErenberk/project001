package com.akselerenberk.bookstore.endpoints;

import com.akselerenberk.bookstore.api.AuthApi;
import com.akselerenberk.bookstore.core.services.AccountService;
import com.akselerenberk.bookstore.dto.CredentialsDTO;
import com.akselerenberk.bookstore.dto.HelloWorldDTO;
import com.akselerenberk.bookstore.dto.TokenDTO;
import com.akselerenberk.bookstore.mappers.CredentialsMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountEndpoint implements AuthApi {

    private final AccountService accountService;

    @Override
    public ResponseEntity<Void> registerUser(final CredentialsDTO credentialsDTO) {
        val credentials = CredentialsMapper.mapDtoToModel(credentialsDTO);

        accountService.registerNewAccount(credentials);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<TokenDTO> authenticateUser(final CredentialsDTO credentialsDTO) {
        val credentials = CredentialsMapper.mapDtoToModel(credentialsDTO);

        val token = accountService.authenticateAccount(credentials);

        val tokenDTO = TokenDTO.builder().token(token.token()).build();

        return ResponseEntity.ok(tokenDTO);
    }

    @Override
    public ResponseEntity<HelloWorldDTO> helloWorld() {
        return ResponseEntity.ok(HelloWorldDTO.builder().helloWorld(HelloWorldDTO.HelloWorldEnum.HELLO_WORLD).build());
    }

}