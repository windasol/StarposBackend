package Starpos.starpos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_auth")
public class UserAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authSeq;
	private String userId;
	private String auth;
	
	public UserAuth(String userId, String auth) {
		this.userId = userId;
		this.auth = auth;
	}
	
}
