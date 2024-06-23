package com.restapi.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.blogapp.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {

	
}
