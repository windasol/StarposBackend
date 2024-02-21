package Starpos.starpos.entity;

import Starpos.starpos.dto.EquipInfoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class EquipInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;    
    private Integer orders;
    private String name;
    private Integer levels;
    private String imgurl;
    private Integer starpos;
    private Integer maxstarpos;
    private Integer changecount;
    private Integer upgradecount;
    private String job;
    private String equiptype;
    private String userid;      
    
    public EquipInfo() {
    	
    }


    @Builder
	public EquipInfo(Long seq, Integer orders, String name, Integer levels, String imgurl, Integer starpos,
			Integer maxstarpos, Integer changecount, Integer upgradecount, String job, String equiptype,
			String userid) {		
		this.seq = seq;
		this.orders = orders;
		this.name = name;
		this.levels = levels;
		this.imgurl = imgurl;
		this.starpos = starpos;
		this.maxstarpos = maxstarpos;
		this.changecount = changecount;
		this.upgradecount = upgradecount;
		this.job = job;
		this.equiptype = equiptype;
		this.userid = userid;
	}
    
    
    public EquipInfoDto toDto() {
    	return EquipInfoDto.builder()
			.seq(seq)
			.orders(orders)
			.name(name)
			.levels(levels)
			.imgurl(imgurl)
			.starpos(starpos)
			.maxstarpos(maxstarpos)
			.changecount(changecount)
			.upgradecount(upgradecount)
			.job(job)
			.equiptype(equiptype)
			.userid(userid)
			.build();
    }
    
}
