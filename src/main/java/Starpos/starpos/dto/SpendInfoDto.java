package Starpos.starpos.dto;

import Starpos.starpos.entity.SpendInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SpendInfoDto {	
	private int seq;
	private String type;
	private int orders;
	private String name;
	private String description;
	private int count;
	private String imgUrl;
	private String userId;	
	
	public SpendInfo toEntity() {
		return SpendInfo.builder()
			.seq(seq)
			.type(type)
			.orders(orders)
			.name(name)
			.description(description)
			.count(count)
			.imgUrl(imgUrl)
			.userId(userId)
			.build();
	}	
}
