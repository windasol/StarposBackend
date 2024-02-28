package Starpos.starpos.entity;

import Starpos.starpos.dto.EquipInfoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seq", referencedColumnName = "itemSeq")
	private Stats stats;
    
    public EquipInfoDto toDto() {
    	return EquipInfoDto.builder()
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
