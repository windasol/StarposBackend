package Starpos.starpos.dto;

import Starpos.starpos.entity.EquipInfo;
import Starpos.starpos.entity.Stats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipInfoDto {
	
	private Long seq;    
	private String type;
    private Integer orders;
    private String name;
    private Integer level;
    private String imgUrl;
    private Integer starpos;
    private boolean starposFinish;
    private Integer maxStarpos;
    private Integer changeCount;
    private Integer isChange;
    private Integer upgradeCount;    
    private String job;
    private String equipType;
    private boolean destroy;
    private String userId;        
    private Stats stats;

    public EquipInfo toEntity() {
    	return EquipInfo.builder()
			.seq(seq)
			.type(type)
			.orders(orders)
			.name(name)
			.level(level)
			.imgUrl(imgUrl)
			.starpos(starpos)
			.starposFinish(starposFinish)
			.maxStarpos(maxStarpos)
			.changeCount(changeCount)
			.isChange(isChange)
			.upgradeCount(upgradeCount)
			.job(job)
			.equipType(equipType)
			.userId(userId)
			.stats(stats)
			.destroy(destroy)
			.build();
    }


}
