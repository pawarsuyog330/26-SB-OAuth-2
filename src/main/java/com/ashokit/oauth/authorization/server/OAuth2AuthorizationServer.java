package com.ashokit.oauth.authorization.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@SuppressWarnings("deprecation")
@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
    UserDetailsService service;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authManager).userDetailsService(service);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			   .withClient("clientapp")
			   .secret(passwordEncoder.encode("654321"))
			   .authorizedGrantTypes("authorization_code", "refresh_token", "password")
			   .scopes("read")
			   .redirectUris("http://localhost:5454/auth_code")
			   .accessTokenValiditySeconds(100)
			   .refreshTokenValiditySeconds(600);
	}
}
