package com.ph.DriverPH.module.auth.repository;

import com.ph.DriverPH.module.auth.entity.AuthUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthUserRepository extends CrudRepository<AuthUser, Long> {

    Optional<AuthUser> findAuthUserById(Long id);
    Optional<AuthUser> findAuthUserByUsername(String userName);
}
