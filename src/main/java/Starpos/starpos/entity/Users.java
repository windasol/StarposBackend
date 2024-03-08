package Starpos.starpos.entity;

import java.util.List;

import Starpos.starpos.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
		
	@OneToMany
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private List<UserAuth> userAuth;
	
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
			.userAuth(userAuth)
			.build();
	}
	
}


