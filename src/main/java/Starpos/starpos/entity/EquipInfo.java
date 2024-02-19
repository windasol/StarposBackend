package Starpos.starpos.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class EquipInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    
    private Integer orders;
    private String name;
    private Integer levels;
    private String imgUrl;
    private Integer starpos;
    private Integer maxStarpos;
    private Integer changeCount;
    private Integer upgradeCount;
    private String job;
    private String equipType;
    private String userId;
}
