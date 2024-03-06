package Starpos.starpos.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
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
}
