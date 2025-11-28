package com.akselerenberk.bookstore.database.repositories;

import com.akselerenberk.bookstore.database.entities.AccountEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

/**
 * Repository for the {@link AccountEntity} JPA entity. Any custom methods, not
 * already defined in {@link ListCrudRepository}, are to be defined here
 * <p>
 * <p>
 * JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
 * <p>
 * Their main functions are:
 * - CrudRepository mainly provides CRUD functions. ListCrudRepository simply uses Lists instead of Iterables.
 * - PagingAndSortingRepository provides methods to do pagination and sorting records.
 * - JpaRepository provides some JPA-related methods such as flushing the persistence context and deleting records in a batch.
 * <p>
 * Because of the inheritance mentioned above, JpaRepository will have all the functions of CrudRepository and PagingAndSortingRepository.
 * So if you don't need the repository to have the functions provided by JpaRepository and PagingAndSortingRepository , use CrudRepository.
 */
public interface AccountRepository extends ListCrudRepository<AccountEntity, Integer> {
    Optional<AccountEntity> findByUsername(String username);
}
