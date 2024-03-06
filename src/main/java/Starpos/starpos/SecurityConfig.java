package Starpos.starpos;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig {
	
	// 암호화 알고리즘 방식 : Bcrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain seucrityFilterChain(HttpSecurity http) throws Exception  {
		log.info("시큐리티 설정");
		
		// 폼 기반 로그인 비활성화
		http.formLogin(login -> login.disable());
		
		// HTTP 기본 인증 비활성화
		http.httpBasic(basic -> basic.disable());
		
		// CSRF(Cross-Site Request Forgery) 공격 방어 기능 비활성화
		http.csrf(csrf -> csrf.disable());
		
		// 필터설정
		http.addFilterAt(null, null)
			.addFilterBefore(null, null);
				
		// 인가설정
		http.authorizeHttpRequests( atuhorizeReuqest -> 
			atuhorizeReuqest				
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") //여러개 설정
				.requestMatchers("/admin/**").hasRole("ADMIN") //단일 설정
				.anyRequest().authenticated()
		);
		
		// 인증방식 설정 (메모리방식 ,jdbc 방식 두가지가 있는데 커스텀가능) 
		http.userDetailsService(null);
			
		
		return http.build();
	}
	
}
