package com.kerimfettahoglu.usermanagement.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CreateAppUserRequest {
	@NotEmpty(message = "email adresi boş olamaz.")
	private String email;
	@NotEmpty(message = "ilk ad boş olamaz.")
	private String firstname;
	@NotEmpty(message = "soyadı boş olamaz.")
	private String lastname;
	@NotEmpty(message = "şifre boş olamaz.")
	private String password;
}
