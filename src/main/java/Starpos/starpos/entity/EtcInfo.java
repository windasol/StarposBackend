package Starpos.starpos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class EtcInfo {
	
	@Id
	private int seq;
	private String type;
	private int orders;
	private String name;
	private String description;
	private String imgurl;
	
	@Builder
	public EtcInfo(int seq, String type, int orders, String name, String description, String imgurl) {		
		this.seq = seq;
		this.type = type;
		this.orders = orders;
		this.name = name;
		this.description = description;
		this.imgurl = imgurl;
	}
	
	
}
