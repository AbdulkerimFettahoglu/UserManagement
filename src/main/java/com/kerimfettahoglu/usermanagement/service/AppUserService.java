package com.kerimfettahoglu.usermanagement.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kerimfettahoglu.usermanagement.entity.AppUser;
import com.kerimfettahoglu.usermanagement.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> appUser = Optional.ofNullable(appUserRepository.findByEmail(username));
		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return appUser.get();
	}

}