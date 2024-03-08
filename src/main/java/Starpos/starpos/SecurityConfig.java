package Starpos.starpos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Starpos.starpos.auth.CustomUserDetailService;
import Starpos.starpos.jwt.JwtAuthenticationFilter;
import Starpos.starpos.jwt.JwtRequestFilter;
import Starpos.starpos.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


// JWT x Security 6 - 컨트롤러 & 권한 설정 14:49

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
		
	private AuthenticationManager authenticationManager;
//	
//	@Bean    
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return this.authenticationManagerBean();
//    }
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
		
	
	
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
		// addFilterAt -> 첫번째는 설정해줄 필터,두번 째는 어떤 필터 에대해서 동작을 시킬건지 필터 클래스
		// addFilterBefore -> 
		http.addFilterAt(new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
				
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
		http.userDetailsService(customUserDetailService);
			
		
		return http.build();
	}
	
	// AuthenticationManager 빈 등록
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
//		return authenticationConfiguration.getAuthenticationManager();
//		return authenticationManager;
	}
	
}
