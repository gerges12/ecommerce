package com.example.ecomerce.website.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.ui.LoginPageGeneratingWebFilter;


@Configuration
@EnableWebSecurity

public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	private final String[] url= {
			"/luxury",
			"/login",
			"/logout",
			"/register",
			"/processRegister",
			"/Rest/password",
			"/Rest/passwordProcess",
			"/Rest/send/*",
			"/Rest/RestProcess",
			"/Rest/show/*",
			"/api/auth/login",
	};
    @Autowired
    private AppUserDetailServise userDetailsService;
  @Bean
  public PasswordEncoder passwordencoder()
  {
	  return new BCryptPasswordEncoder();
  }
  @Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
  throws Exception {
  auth.userDetailsService(userDetailsService);
  
  }
  @Override
 public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resourses/**");
 	}
  @Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
		.authorizeRequests()
	     .antMatchers(url).permitAll()
	     .antMatchers("/addProduct").hasAnyAuthority("admin")
	     .anyRequest()
	     .authenticated()
	     .and()
	      .formLogin().loginPage("/login").defaultSuccessUrl("/luxury")
	     .and()
	     .logout().logoutUrl("/logout")
	    .and()
	   .csrf().disable()
	   .exceptionHandling().accessDeniedPage("/web/403")
	   .and()
	   .httpBasic();
	  

	
	}
 
}
