package Starpos.starpos.entity;

import Starpos.starpos.dto.EtcInfoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EtcInfo {
	
	@Id
	private int seq;
	private String type;
	private int orders;
	private String name;
	private String description;
	private String imgUrl;
	private String userId;
	
	public EtcInfoDto toDto() {
		return EtcInfoDto.builder()
			.seq(seq)
			.type(type)
			.orders(orders)
			.name(name)
			.description(description)
			.imgUrl(imgUrl)
			.userId(userId)
			.build();
	}
	
}
