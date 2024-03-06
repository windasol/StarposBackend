package Starpos.starpos.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Starpos.starpos.entity.Users;
import Starpos.starpos.repository.UserRepository;
import Starpos.starpos.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserServiceImpl userServiceImpl;	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("login - loadUseByUSERname: " + username);
		
		Users user = userServiceImpl.login(null, null);
		
		if (user == null) {
			log.info("사용자 없없음... (일치하는 아이디가 없음)");
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다." + username);
		}
		
		log.info(username);
			
		
		// JWT x Security 3 - 시큐리티 설정 23: 08초
		
		return null;
	}
	
	
}
