package com.curso.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.curso.security.service.UsuarioService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
	@Autowired 
	private UsuarioService usuarioService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/webjars/**","/css/**","/image/**", "/js/**").permitAll()
			.antMatchers("/","/home").permitAll() // autorizando acesso públic a home	
			
			// acessos privados ADMIN
			.antMatchers("/u/**").hasAuthority("ADMIN") 
			//acessos privados  MEDICOS
			.antMatchers("/medicos/**").hasAnyAuthority("MEDICO","ADMIN") 
			.anyRequest().authenticated()
		.and()
			.formLogin() // permitindo acesso ao login
			.loginPage("/login")
			.defaultSuccessUrl("/",true)
			.failureUrl("/login-error") // em caso de login incorreto
			.permitAll() // mensagem de erro e retorno para a página de login. 
		.and()
			.logout()
			.logoutSuccessUrl("/") // voltando a página inicial
		.and()
			.exceptionHandling()
			.accessDeniedPage("/acesso-negado"); // página de acesso negado
			
		}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	
	
}
