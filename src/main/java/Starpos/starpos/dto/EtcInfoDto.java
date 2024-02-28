package Starpos.starpos.dto;

import Starpos.starpos.entity.EtcInfo;
import Starpos.starpos.entity.Stats;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EtcInfoDto {
	
	private int seq;
	private String type;
	private int orders;
	private String name;
	private String description;
	private String imgUrl;
	private String userId;
		
	public EtcInfo toDto() {
		return EtcInfo.builder()
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
