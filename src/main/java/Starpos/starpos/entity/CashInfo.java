package Starpos.starpos.entity;

import Starpos.starpos.dto.CashInfoDto;
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
public class CashInfo {
	
	@Id
	private int seq;
	private String type;
	private int orders;
	private String name;
	private int level;	
	private String imgUrl;
	private String userId;
	
	public CashInfoDto toDto() {
		return CashInfoDto.builder()
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
