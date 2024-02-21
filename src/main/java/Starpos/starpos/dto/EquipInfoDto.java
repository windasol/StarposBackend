package Starpos.starpos.dto;

import Starpos.starpos.entity.EquipInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipInfoDto {
	
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

    @Builder
	public EquipInfoDto(Long seq, Integer orders, String name, Integer levels, String imgurl, Integer starpos,
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
    
    public EquipInfo toEntity() {
    	return EquipInfo.builder()
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
