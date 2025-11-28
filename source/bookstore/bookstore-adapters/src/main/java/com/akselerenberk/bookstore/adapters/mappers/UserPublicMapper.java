package com.akselerenberk.bookstore.adapters.mappers;

import com.akselerenberk.bookstore.core.models.UserPublic;
import com.akselerenberk.bookstore.database.entities.AccountEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserPublicMapper {
    public UserPublic map(AccountEntity account) {
        return UserPublic.builder()
                .username(account.getUsername())
                .verificationLevel(account.getVerificationLevel())
                .build();
    }
}
