package Starpos.starpos.serviceImpl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import Starpos.starpos.dto.UserDto;
import Starpos.starpos.entity.UserAuth;
import Starpos.starpos.entity.Users;
import Starpos.starpos.repository.UserAuthRepository;
import Starpos.starpos.repository.UserRepository;
import Starpos.starpos.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
		
	private final PasswordEncoder passwordEncoder;
		
	private final UserRepository userRepository;
		
	private final UserAuthRepository userAuthRepository;
		
	private final AuthenticationManager authenticationManager;	
		
	
	// 저장
	@Override
	public int insert(UserDto dto) {
		String userPw = dto.getUserPw();
		String encodePw = passwordEncoder.encode(userPw);
		dto.setUserPw(encodePw);
		
		Users user = Users.builder()
				.userSeq(0)
				.userId(dto.getUserId())
				.userPw(dto.getUserPw())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.name(dto.getName())
				.isLogin(dto.isLogin())
				.status(dto.getStatus())
				.authority(dto.getAuthority())
				.build();				
		
		Users save = userRepository.save(user);
		if (save != null) {
			UserAuth userAuth = UserAuth.builder()
					.authSeq(0)
					.userId(dto.getUserId())
					.auth("ROLE_USER")
					.build();
			userAuthRepository.save(userAuth);
			
		}
		return 0;
	}

	// 조회
	@Override
	public Optional<UserDto> select(int seq) {	
		return userRepository.findById(seq).map(Users::toDto);
	}

	// 로그인
	@Override
	public void login(UserDto user, HttpServletRequest rquest) {
		String id = user.getUserId();
		String pw = user.getUserPw();
		log.info("id : " + id);
		log.info("pw : " + pw);
		
		// 아이디 ,패스워드 인증 토큰 생성
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, pw);
		
		// 토큰에 요청ㅈ어보 등록
		token.setDetails(new WebAuthenticationDetails(rquest));
		
		// 토큰을 이용하여 인증 요청 - 로그인		
		Authentication authentication = authenticationManager.authenticate(token);
		
		log.info("인증 여부 : " + authentication.isAuthenticated());
		
		// 스프링 시큐리티 유저 정보 객체임 (우리가만든 db 데이터x)
		Users authUser = (Users)authentication.getPrincipal();
		log.info("인증된 사용자 : " + authUser.getUserId());
		
		// 시큐리티 컨텍스트에 인증된 사용자 등록
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	// 수정
	@Override
	public int update(UserDto dto) {
		String userPw = dto.getUserPw();
		String encodePw = passwordEncoder.encode(userPw);
		dto.setUserPw(encodePw);
		
		Users user = Users.builder()
				.userSeq(0)
				.userId(dto.getUserId())
				.userPw(dto.getUserPw())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.name(dto.getName())
				.isLogin(dto.isLogin())
				.status(dto.getStatus())
				.authority(dto.getAuthority())
				.build();				
		
		Users save = userRepository.save(user);
		return 0;
	}

	@Override
	public void delete(int seq) {		
		userRepository.deleteById(seq);
	}
	
	
}
