package com.kerimfettahoglu.usermanagement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kerimfettahoglu.usermanagement.controller.dto.CreateAppUserRequest;
import com.kerimfettahoglu.usermanagement.entity.AppUser;
import com.kerimfettahoglu.usermanagement.service.AppUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class AppUserController {
	
	private final AppUserService appUserService;
	
	@GetMapping("all")
	public List<AppUser> findAll() {
		return appUserService.findAll();
	}
	
	@PostMapping
	public boolean save(@RequestBody CreateAppUserRequest createAppUserRequest) {
		return true;
	}
}
