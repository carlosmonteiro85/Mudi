//package br.com.alura.mvc.mudi.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			//requisiçoes autorizadas
//			.authorizeRequests()
//			//qualquer requisição tem q ser autenticada
//				.anyRequest().authenticated()
//			.and()
//				.formLogin(form -> form 
//						//url da pagina de login
//						.loginPage("/login")
//						//todos os usuarios estão permitidos a esta url
//						.permitAll());
//	}
//	//usuario salvo em memória
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("asdf")
//				.password("asdf")
//				.roles("ADM")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
//	
//	
//}
