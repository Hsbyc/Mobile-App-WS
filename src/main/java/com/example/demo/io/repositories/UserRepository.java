package com.example.demo.io.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	//Spring Repository class deal with all databases work for us. Only methods provided by developer.
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	
}
