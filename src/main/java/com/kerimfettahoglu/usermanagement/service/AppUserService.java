package com.kerimfettahoglu.usermanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kerimfettahoglu.usermanagement.configuration.PasswordEncoder;
import com.kerimfettahoglu.usermanagement.controller.dto.CreateAppUserRequest;
import com.kerimfettahoglu.usermanagement.controller.dto.UpdateAppUserRequest;
import com.kerimfettahoglu.usermanagement.entity.AppUser;
import com.kerimfettahoglu.usermanagement.repository.AppUserRepository;
import com.kerimfettahoglu.usermanagement.service.exception.AppUserNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	public String save(CreateAppUserRequest createAppUserRequest) {
		AppUser user = new AppUser();
		user.setEmail(createAppUserRequest.getEmail());
		user.setFirstName(createAppUserRequest.getFirstname());
		user.setLastName(createAppUserRequest.getLastname());
		user.setPassword(bCryptPasswordEncoder.encode(createAppUserRequest.getPassword()));
		appUserRepository.save(user);
		return createToken(createAppUserRequest);
	}
	
	private String createToken(CreateAppUserRequest user) {
		String username = user.getEmail();
		String password = user.getPassword();
		log.info("username :{}, password:{}", username, password);
		Algorithm algo = Algorithm.HMAC256(PasswordEncoder.ALGORITHM_KEY.getBytes());
		String accessToken = JWT.create().withSubject(username).withExpiresAt(new Date(System.currentTimeMillis() + (10 * 60 * 1000))).withIssuer("com.kerimfettahoglu").sign(algo);
		return accessToken;
	}
	
	public AppUser findUser(Long id) {
		Optional<AppUser> user = appUserRepository.findById(id);
		if (user.isEmpty())
			throw new AppUserNotFoundException(id);
		return user.get();
	}

	public Boolean deleteUser(Long id) {
		Optional<AppUser> user = appUserRepository.findById(id);
		if (user.isEmpty())
			throw new AppUserNotFoundException(id);
		appUserRepository.delete(user.get());
		return true;
	}
	
	public Boolean updateUser(UpdateAppUserRequest updateRequest) {
		Optional<AppUser> user = appUserRepository.findById(updateRequest.getId());
		if (user.isEmpty())
			throw new AppUserNotFoundException(updateRequest.getId());
		AppUser newUser = user.get();
		newUser.setEmail(updateRequest.getEmail());
		newUser.setFirstName(updateRequest.getFirstname());
		newUser.setLastName(updateRequest.getLastname());
		newUser.setPassword(bCryptPasswordEncoder.encode(updateRequest.getPassword()));
		appUserRepository.save(newUser);
		return true;
	}
}
