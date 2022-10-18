package com.kerimfettahoglu.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kerimfettahoglu.usermanagement.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findByEmail(String email);
}
