package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/home/**")
			.permitAll() //paginas permitidas acessar sem autenticação
		.anyRequest()
			.authenticated() // todas as requisições precisa
		.and()
			.formLogin(form -> form
					.loginPage("/login")// URL da pagina de login
					.defaultSuccessUrl("/usuario/pedido", true) //pagina usada ao autenticar
					.permitAll() // todos são permitidos acessar a pagina de login
				)
			 		.logout(logout -> {
			 			logout.logoutUrl("/logout")
			 				.logoutSuccessUrl("/home");
			 		}).csrf().disable();
			 		 //para que seja enviada as requisiçoes do formulario
		
//			 		.logout(logout -> logout.logoutUrl("/logout"))
//			 		.csrf().disable(); //para que seja enviada as requisiçoes do formulario
		
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //gerador de senhas criptografadas
//		
//////		apenas para criar um usuario no banco
//		UserDetails user =
//				 User.builder() //Contruindo um usuario com a aplicação
//					.username("asdf2") // user name do usuario
//					.password(encoder.encode("asdf")) // criando o password e criptografando
//					.roles("ADM")     // tipo
//					.build();
//		
//		
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)//instanciando a conexão
//		.passwordEncoder(encoder) //criptografar a senha
//		
//		.withUser(user); // apenas se estiver criando um usuario a cima
//	}
	
	
	@Bean
	@Override
	//Criando um usuario em memoria
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder() //Meodo apenas para fins de testes, não seguro para produção
				.username("asdf") // user name do usuario
				.password("asdf") // password
				.roles("ADM")     // tipo
				.build();

		return new InMemoryUserDetailsManager(user);
	}

}
