package com.ashokit.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

	private String name;
	
	private String email;
	
	private String status;
}
