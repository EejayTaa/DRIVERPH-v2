package com.ph.DriverPH.module.auth.repository;

import com.ph.DriverPH.module.auth.entity.AuthUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
@Repository
public interface IAuthUserRepository extends CrudRepository<AuthUser, Long>, PagingAndSortingRepository<AuthUser, Long> {

    Optional<AuthUser> findAuthUserById(Long id);

    Optional<AuthUser> findAuthUserByUsername(String userName);


    void deleteAuthUserByUsername(String id);
}
