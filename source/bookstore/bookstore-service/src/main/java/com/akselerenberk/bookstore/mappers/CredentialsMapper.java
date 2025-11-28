package com.akselerenberk.bookstore.mappers;

import com.akselerenberk.bookstore.core.models.Credentials;
import com.akselerenberk.bookstore.dto.CredentialsDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CredentialsMapper {
    public Credentials mapDtoToModel(final CredentialsDTO dto) {
        return Credentials.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}