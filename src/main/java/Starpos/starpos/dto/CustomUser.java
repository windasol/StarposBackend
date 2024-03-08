package Starpos.starpos.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Starpos.starpos.entity.UserAuth;
import Starpos.starpos.entity.Users;
import lombok.Data;

@Data
public class CustomUser implements UserDetails {

	private Users user;
	
	public CustomUser(Users user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<UserAuth> authList = user.getUserAuth();
		Collection<SimpleGrantedAuthority> roleList = authList.stream()
										.map((auth) -> new SimpleGrantedAuthority(auth.getAuth()))
										.collect(Collectors.toList());	
		return roleList;
	}

	@Override
	public String getPassword() {
		return user.getUserPw();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return user.getStatus().equals("N") ? false : true;
	}
	
	

}
