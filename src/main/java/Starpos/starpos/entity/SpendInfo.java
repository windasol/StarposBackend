package Starpos.starpos.entity;

import Starpos.starpos.dto.SpendInfoDto;
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
public class SpendInfo {
	
	@Id
	private int seq;
	private String type;
	private int orders;
	private String name;
	private String description;
	private int count;
	private String imgUrl;
	private String userId;
	
	public SpendInfoDto toDto() {
		return SpendInfoDto.builder()
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
