package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
		   
		    http.csrf().disable();
		    http.headers().frameOptions().disable();
		    
	        http.authorizeRequests()
	                .antMatchers("/signup","/", "/login","/css/**", "/js/**","/h2-console/**").permitAll()
	                .anyRequest().authenticated();
	        
	                 http.formLogin()
	                .loginPage("/login")
	                .permitAll();

	        http.formLogin()
	                .defaultSuccessUrl("/home", true);
	        
	        http.logout()
	        .invalidateHttpSession(true)
	        .clearAuthentication(true)
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        .logoutSuccessUrl("/login?logout");

	        
	    //    http.logout()
	    //    .logoutUrl("/logouttt");
	        
	  //      http.logout()
	   //     .logoutSuccessUrl("/login?lougout");
	    }

}
