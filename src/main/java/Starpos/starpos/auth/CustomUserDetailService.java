package Starpos.starpos.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Starpos.starpos.dto.CustomUser;
import Starpos.starpos.entity.Users;
import Starpos.starpos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;	

	// 시큐리티가 로그인 할때 이 메소드를 사용하겠다
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("login - loadUseByUSERname: " + username);
		
		Optional<Users> user = userRepository.findByUserId(username);
					
		if (user.isEmpty()) {
			log.info("사용자 없없음... (일치하는 아이디가 없음)");
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다." + username);
		}
		
		log.info(username);
			
		CustomUser customUser = new CustomUser(user.get());
		 
		return customUser;
	}
	
	
}
