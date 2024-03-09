package Starpos.starpos.jwt;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import Starpos.starpos.auth.JwtProps;
import Starpos.starpos.dto.CustomUser;
import Starpos.starpos.dto.UserDto;
import Starpos.starpos.entity.UserAuth;
import Starpos.starpos.entity.Users;
import Starpos.starpos.serviceImpl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *  JWT 토큰 관련 기능을 제공해주는 클래스
 *  1. 토큰 생성
 *  2. 토큰 해석
 *  3. 토큰 유효성 검사
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
		
	private final JwtProps jwtProps;
	private final UserServiceImpl userServiceImpl;

	/**
	 * 토큰 생성	  
	 */
	public String createToken(int userNo, String userId, List<String> roles) {
				
		String jwt = Jwts.builder()
				.signWith(getShaKey(), Jwts.SIG.HS512) // 서명에 사용할 키와 알고리즘 설정
				.header()											   // deprecated 
					.add("typ", JwtConstants.TOKEN_TYPE)               // 헤더 설정 (JWT)
				.and()
				.expiration(new Date(System.currentTimeMillis() + 864000000)) // 토큰 만료 시간 설정 (10일)
				.claim("uno", userNo)										  // 클레임 설정 : 사용자 번호
				.claim("uid", userId)										  // 클레임 설정 : 사용자 아이디
				.claim("role", roles)										  // 클레임 설정 : 권한
				.compact();
		
		log.info("jwt" + jwt);
		
		return jwt;						
	}
	
	/**
	 * 토근해석
	 */
	public UsernamePasswordAuthenticationToken getAutehntication(String authHeader) {
		if (authHeader == null || authHeader.length() == 0) {
			return null;
		}
		
		try {
			// JWT 추출 (Bearer + {jwt} - > {jwt})
			String jwt = authHeader.replace(JwtConstants.TOKEN_PREFIX, "");
			
			// JWT 파싱
			Jws<Claims> parsedToken = Jwts.parser()
										.verifyWith(getShaKey())
										.build()
										.parseSignedClaims(jwt);
			
			log.info("parsedToken : " + parsedToken);
			
			// 인증된 사용자 번호
			String userNo = parsedToken.getPayload().get("uno").toString();
			int no = (userNo == null ? 0 : Integer.parseInt(userNo));
			log.info("userNo : " + userNo);
			
			String userId = parsedToken.getPayload().get("uid").toString();
			log.info("userId : " + userId);
			
			Claims claims = parsedToken.getPayload();
			Object roles = claims.get("rol");
			log.info("roles : " + roles);
			
			if(userId == null || userId.length() == 0) {
				return null;
			}
			
			UserDto dto = new UserDto();
			dto.setUserSeq(no);
			dto.setUserId(userId);			
			
			// 권한 담기
			List<UserAuth> authList = ((List<?>) roles).stream()
					.map(auth -> new UserAuth(userId, auth.toString()))
					.collect(Collectors.toList());
			
			dto.setUserAuth(authList);
			
			// customUser 에 권한 담기
			List<SimpleGrantedAuthority> authorities = ((List<?>) roles)
														.stream()
														.map(auth -> new SimpleGrantedAuthority(auth.toString()))
														.collect(Collectors.toList());
			
			// 토크 유효하면
			// name, email 도 담아주기
			try {
				Optional<UserDto> userInfo = userServiceImpl.select(no);
				if (!userInfo.isPresent()) {
					userInfo.ifPresent(d -> {
						dto.setName(d.getName());
						dto.setEmail(d.getEmail());					
					});
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				log.error("토큰 윻 -> DB 추가 정보 조회시 에러 발생...");
			}
			
			Users user = dto.toEntity();			
			UserDetails userDetails = new CustomUser(user);
			
			// new UsernamePasswordAuthenticationToken (사용자정보객체 , 비밀번호, 사용자의 권한)
			return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
			
		} catch (ExpiredJwtException exception) {
			log.warn("rquest to parse expired JWT : {} failed {}", authHeader, exception.getMessage());
		} catch (UnsupportedJwtException exception) {
			log.warn("rquest to parse unsupported JWT : {} failed {}", authHeader, exception.getMessage());
		} catch (MalformedJwtException exception) {
			log.warn("rquest to parse invalid JWT : {} failed {}", authHeader, exception.getMessage());
		} catch (IllegalArgumentException exception) {
			log.warn("rquest to parse empty JWT : {} failed {}", authHeader, exception.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 토큰 유효성 검사
	 * - 만료기간이 넘었는지?
	 * @param jwt
	 * @return
	 * 1. 유효 true 
	 * 2. 만료 false
	 */
	public boolean validateToken(String jwt) {		
		try {
			// JWT 파싱
			Jws<Claims> parsedToken = Jwts.parser()
					.verifyWith(getShaKey())
					.build()
					.parseSignedClaims(jwt);
			
			Date exp = parsedToken.getPayload().getExpiration();
			
			log.info("###### 토큰 만료기간 ######");
			log.info("->" + exp);
			
			// 만료시간과 현재시간 비교
			// 만료 : false, 유효 : true
			return !exp.before(new Date());			
		} catch(ExpiredJwtException e) {
			log.error("Token Expired");
			return false;
		} catch (JwtException e) {
			log.error("Token Tempered");
			return false;
		} catch (NullPointerException e) {
			log.error("Token is Null");
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	// secretKey -> signKey
	private byte[] getSignKey() {
		return jwtProps.getSecretKey().getBytes();
	}
	
	// secretKey -> (HMAC -SHA algorithms) -> signKey
	private SecretKey getShaKey() {
		return Keys.hmacShaKeyFor(getSignKey());
	}
	
}
