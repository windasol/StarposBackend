package Starpos.starpos.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Starpos.starpos.dto.CustomUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**             (/login 경로일떄) 
 *  client 요청 -> filter -> server
 *  username, password 로 인증시도 attemptAuthentication함수 호출
 *  인증 실패 시 : response > status : 401   
 *  인증 성공 시 successfulAuthentication 호출
 *    -> JWT 토큰 생성 (response > headers > autorization : (JWT))
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
		
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		// 필터 URL 경로 설정 : /login
		setFilterProcessesUrl(JwtConstants.AUTH_LOGIN_URL);
	}
	
	/**
	 *  인증 시도 메소드
	 *  : /login 경로로 요청하면, 필터로 걸러서 인증을 시도
	 *  filter 를 어떤 경로에만 거를 것인지 (login 경로 에서만)
	 */
	@Override
	@Transactional
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		// 파라미터 확인 해야됨 id, pw 처럼
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.info("username : " + username);
		log.info("password : " + password);
		
		// 사용자 인증정보 객체 생성
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
		
		// 사용자 인증 (로그인) -- PasswordEncdoer 빈등록 해놔서 자동으로 타게 됨
		authentication = authenticationManager.authenticate(authentication);
		
		log.info("인증 여부 : " + authentication.isAuthenticated());
		
		// 인증 실패 (username, password 불일치)
		if (!authentication.isAuthenticated()) {
			log.info("인증 실패 : 아이디 또는 비밀번호가 일치하지 않습니다.");
			response.setStatus(401); // 401 unauthorized (인증 실패)
		}
		
		return authentication;
	}

	/**
	 * 인증 성공 메소드
	 * 
	 * - JWT 생성
	 * - JWT 를 응답 헤더에 설정
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("인증 성공...");
		
		CustomUser user = (CustomUser) authentication.getPrincipal();
		
		int userNo = user.getUser().getUserSeq();
		String userId = user.getUser().getUserId();
		List<String> roles = user.getUser().getUserAuth().stream()
													.map((auth) -> auth.getAuth().toString())
													.collect(Collectors.toList());
		
		// JWT 토큰 생성
		String jwt = jwtTokenProvider.createToken(userNo, userId, roles);
		
		// { authorization : Bearer + {jwt} }
		response.addHeader(JwtConstants.TOKEN_HEADER, JwtConstants.TOKEN_PREFIX + jwt);
		response.setStatus(200); 
		
	}
	
	
}
