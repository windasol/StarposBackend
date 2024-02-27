package Starpos.starpos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class SpendInfo {
	
	@Id
	private int seq;
	private String type;
	private int orders;
	private String name;
	private String description;
	private int count;
	private String imgurl;
	
	@Builder
	public SpendInfo(int seq, String type, int orders, String name, String description, int count, String imgurl) {		
		this.seq = seq;
		this.type = type;
		this.orders = orders;
		this.name = name;
		this.description = description;
		this.count = count;
		this.imgurl = imgurl;
	}
	
	
}
