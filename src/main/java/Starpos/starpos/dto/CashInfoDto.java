package Starpos.starpos.dto;

import Starpos.starpos.entity.CashInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CashInfoDto {	
	
	private int seq;
	private String type;
	private int orders;
	private String name;
	private int level;	
	private String imgUrl;
	private String userId;
	
	public CashInfo toEntity() {
		return CashInfo.builder()
			.seq(seq)
			.type(type)
			.orders(orders)
			.name(name)
			.level(level)
			.imgUrl(imgUrl)
			.userId(userId)
			.build();
	}
	
}
