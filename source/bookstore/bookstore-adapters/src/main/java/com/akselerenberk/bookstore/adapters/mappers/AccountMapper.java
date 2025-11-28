package com.akselerenberk.bookstore.adapters.mappers;

import com.akselerenberk.bookstore.core.models.Account;
import com.akselerenberk.bookstore.database.entities.AccountEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public Account mapEntityToModel(final AccountEntity entity) {
        return Account.builder()
                .verificationLevel(entity.getVerificationLevel())
                .username(entity.getUsername())
                .roles(entity.getRoles())
                .build();
    }

    public AccountEntity mapModelToEntity(final Account model) {
        return AccountEntity.builder()
                .verificationLevel(model.verificationLevel())
                .username(model.username())
                .password(model.password())
                .roles(model.roles())
                .build();
    }

}
