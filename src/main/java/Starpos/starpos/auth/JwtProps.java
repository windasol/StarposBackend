package Starpos.starpos.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("com.dong.server")
public class JwtProps {

	private String secretKey;
}
