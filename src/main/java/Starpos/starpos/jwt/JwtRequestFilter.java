package Starpos.starpos.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtRequestFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	/**
	 * JWT 요청 필터
	 * - request > headers -> authrization (JWT)
	 * - JWT 토큰 유효성 검사
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 헤더에서 JWT 토큰을 가져옴
		String header = request.getHeader(JwtConstants.TOKEN_HEADER);
		log.info("authorization : " + header);
		
		// jwt 토큰이 없으면 다음 필터로 이동
		// Bearer + {jwt} 체크
		if (header == null || header.length() == 0 || !header.startsWith(JwtConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// JWT
		// Bearer + {jwt} -> "Bearer " 제거
		String jwt = header.replace(JwtConstants.TOKEN_PREFIX, "");
		
		log.info("jwt : " + jwt);
				
		// 토큰 해석
		Authentication authentication = jwtTokenProvider.getAutehntication(header);
		
		// 토큰 유효성 검사
		if (jwtTokenProvider.validateToken(jwt)) {
			log.info("유효한 JWT 토큰입니다.");
			
			// 로그인			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		// 다음 필터 (지금은 없지만 추가로 넘길때 사용)
		filterChain.doFilter(request, response);
		
	}
	
	
	
}
