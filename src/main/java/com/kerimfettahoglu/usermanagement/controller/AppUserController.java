package com.kerimfettahoglu.usermanagement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kerimfettahoglu.usermanagement.entity.AppUser;
import com.kerimfettahoglu.usermanagement.service.AppUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class AppUserController {
	
	private final AppUserService appUserService;
	
	@GetMapping("all")
	public List<AppUser> findAll() {
		return appUserService.findAll();
	}
}
