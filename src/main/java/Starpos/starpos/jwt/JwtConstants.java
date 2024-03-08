package Starpos.starpos.jwt;

/**
 * 1. 로그인 필터 경로
 * 2. 토큰 헤더
 * 3. 토큰 헤더의 접두사 (prefix)
 * 4. 토큰 타입
 * 
 */
public class JwtConstants {
	
	public static final String AUTH_LOGIN_URL = "/login";
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";
		
}
