package Starpos.starpos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Starpos.starpos.dto.CustomUser;
import Starpos.starpos.dto.UserDto;
import Starpos.starpos.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userServiceImpl;
	
	/**
	 * 사용자 정보 조회
	 * @param customUser
	 * @return
	 */
	@GetMapping("/info")
	public ResponseEntity<?> userInfo(@AuthenticationPrincipal CustomUser customUser) {
		
		log.info(":::: customer ::::");
		log.info("customUser : " + customUser);
		
		UserDto dto = customUser.getUser().toDto();
				
		if(dto != null) {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		
		return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);		
	}
	
	@PostMapping("/join")
	public void postMethodName(@RequestBody UserDto dto) {
		userServiceImpl.insert(dto);
		
		log.info("회원가입 성공....");
	}
	
	
	
}
