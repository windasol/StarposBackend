package Starpos.starpos.dto;

import java.util.List;

import Starpos.starpos.entity.UserAuth;
import Starpos.starpos.entity.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private int userSeq;
	private String userId;
	private String userPw;
	private String email;
	private int phone;
	private String name;
	private boolean isLogin;
	private String status;
	private String authority;
			
	private List<UserAuth> userAuth;
	
	public Users toEntity() {
		return Users.builder()
			.userSeq(userSeq)
			.userId(userId)
			.userPw(userPw)
			.email(email)
			.phone(phone)
			.name(name)
			.isLogin(isLogin)
			.status(status)
			.authority(authority)
			.userAuth(userAuth)
			.build();
	}
}
