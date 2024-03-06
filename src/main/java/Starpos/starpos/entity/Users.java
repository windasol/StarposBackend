package Starpos.starpos.entity;

import Starpos.starpos.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users{
	@Id
	private int userSeq;
	private String userId;
	private String userPw;
	private String email;
	private int phone;
	private String name;
	private boolean isLogin;
	private String status;
	private String authority;
	
	public UserDto toDto() {
		return UserDto.builder()
			.userSeq(userSeq)
			.userId(userId)
			.userPw(userPw)
			.email(email)
			.phone(phone)
			.name(name)
			.isLogin(isLogin)
			.status(status)
			.authority(authority)
			.build();
	}
	
}


