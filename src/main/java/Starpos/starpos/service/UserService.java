package Starpos.starpos.service;

import java.util.Optional;

import Starpos.starpos.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
	
	// 아이디 생성
	public int insert(UserDto user);
	
	// 아이디 조회
	public Optional<UserDto> select(int seq);
		
	// 로그인
	public void login(UserDto user, HttpServletRequest rquest);
	
	// 회원정보 수정
	public int update(UserDto user);
	
	// 회원 삭제
	public void delete(int seq);
	
}
