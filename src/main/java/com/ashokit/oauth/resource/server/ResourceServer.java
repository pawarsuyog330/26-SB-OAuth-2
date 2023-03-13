package com.ashokit.oauth.resource.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SuppressWarnings("deprecation")
@EnableResourceServer
@Configuration
public class ResourceServer  extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
			.antMatchers("/api/users/me**")
			.and()
			.authorizeRequests()
			.anyRequest()
			.access("#oauth2.hasScope('read')");
	}
}
