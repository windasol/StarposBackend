package Starpos.starpos.dto;

import Starpos.starpos.entity.EquipInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipInfoDto {
	
	private Long seq;    
	private String type;
    private Integer orders;
    private String name;
    private Integer levels;
    private String imgurl;
    private Integer starpos;
    private Integer starposfinish;
    private Integer maxstarpos;
    private Integer changecount;
    private Integer ischange;
    private Integer upgradecount;    
    private String job;
    private String equiptype;
    private String userid;      

    @Builder
   	public EquipInfoDto(Long seq, String type, Integer orders, String name, Integer levels, String imgurl, Integer starpos,
   			Integer starposfinish, Integer maxstarpos, Integer changecount, Integer ischange, Integer upgradecount,
   			String job, String equiptype, String userid) {		
   		this.seq = seq;
   		this.type = type;
   		this.orders = orders;
   		this.name = name;
   		this.levels = levels;
   		this.imgurl = imgurl;
   		this.starpos = starpos;
   		this.starposfinish = starposfinish;
   		this.maxstarpos = maxstarpos;
   		this.changecount = changecount;
   		this.ischange = ischange;
   		this.upgradecount = upgradecount;
   		this.job = job;
   		this.equiptype = equiptype;
   		this.userid = userid;
   	}
    
    public EquipInfo toEntity() {
    	return EquipInfo.builder()
			.seq(seq)
			.type(type)
			.orders(orders)
			.name(name)
			.levels(levels)
			.imgurl(imgurl)
			.starpos(starpos)
			.starposfinish(starposfinish)
			.maxstarpos(maxstarpos)
			.changecount(changecount)
			.ischange(ischange)
			.upgradecount(upgradecount)
			.job(job)
			.equiptype(equiptype)
			.userid(userid)
			.build();
    }

}
