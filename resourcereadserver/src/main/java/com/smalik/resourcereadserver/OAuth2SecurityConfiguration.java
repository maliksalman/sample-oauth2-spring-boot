package com.smalik.resourcereadserver;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
           .authorizeRequests(requests ->
                requests
                     .antMatchers(HttpMethod.GET, "/hero").hasAuthority("SCOPE_grp-read-only-client")
                     .anyRequest().authenticated())
           .oauth2ResourceServer(server ->
                server.jwt())
           .oauth2Client();
   }
}