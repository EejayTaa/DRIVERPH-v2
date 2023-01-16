package com.ph.DriverPH.module.user.repository;


import com.ph.DriverPH.module.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

}
