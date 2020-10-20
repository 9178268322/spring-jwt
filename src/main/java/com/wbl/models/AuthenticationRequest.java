package com.wbl.models;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequest {
	
	@NotNull(message = "Username can't be null")
	private String username;
	
	@NotNull(message = "Password can't be null")
	private String password;
}
