package Starpos.starpos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class CashInfo {
	
	@Id
	private int seq;
	private String type;
	private int orders;
	private String name;
	private int level;	
	private String imgurl;
	
	@Builder
	public CashInfo(int seq, String type, int orders, String name, int level, String imgurl) {		
		this.seq = seq;
		this.type = type;
		this.orders = orders;
		this.name = name;
		this.level = level;		
		this.imgurl = imgurl;
	}
	
	
}
