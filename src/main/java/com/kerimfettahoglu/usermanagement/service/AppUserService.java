package com.kerimfettahoglu.usermanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kerimfettahoglu.usermanagement.controller.dto.CreateAppUserRequest;
import com.kerimfettahoglu.usermanagement.entity.AppUser;
import com.kerimfettahoglu.usermanagement.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> appUser = Optional.ofNullable(appUserRepository.findByEmail(username));
		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return appUser.get();
	}
	
	public List<AppUser> findAll() {
		return appUserRepository.findAll();
	}
	
	public Boolean save(CreateAppUserRequest createAppUserRequest) {
		AppUser user = new AppUser();
		user.setEmail(createAppUserRequest.getEmail());
		user.setFirstName(createAppUserRequest.getFirstname());
		user.setLastName(createAppUserRequest.getLastname());
		user.setPassword(bCryptPasswordEncoder.encode(createAppUserRequest.getPassword()));
		appUserRepository.save(user);
		return true;
	}

}
