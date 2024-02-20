package Starpos.starpos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
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
    
	public Long getSeq() {
		return seq;
	}
	public Integer getOrders() {
		return orders;
	}
	public String getName() {
		return name;
	}
	public Integer getLevels() {
		return levels;
	}
	public String getImgurl() {
		return imgurl;
	}
	public Integer getStarpos() {
		return starpos;
	}
	public Integer getMaxstarpos() {
		return maxstarpos;
	}
	public Integer getChangecount() {
		return changecount;
	}
	public Integer getUpgradecount() {
		return upgradecount;
	}
	public String getJob() {
		return job;
	}
	public String getEquiptype() {
		return equiptype;
	}
	public String getUserid() {
		return userid;
	}
    
    
}
