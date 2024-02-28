package Starpos.starpos.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Starpos.starpos.dto.EquipInfoDto;
import Starpos.starpos.entity.EquipInfo;
import Starpos.starpos.repository.EquipRepository;
import Starpos.starpos.service.EquipService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipInfoServiceImpl implements EquipService {
	
	private final EquipRepository equipRepository;
	
	public List<EquipInfoDto> searchEquip(String userId) {				
		return equipRepository.findByUserId(userId).stream()
				.map(EquipInfo::toDto).collect(Collectors.toList());		
	}
	
	public void upgradeEquip(EquipInfoDto equipDto) {			
		EquipInfo equip = EquipInfo.builder()
			.seq(equipDto.getSeq())
			.type(equipDto.getType())
			.orders(equipDto.getOrders())
			.name(equipDto.getName())
			.level(equipDto.getLevel())
			.imgUrl(equipDto.getImgUrl())
			.starpos(equipDto.getStarpos())
			.starposFinish(equipDto.isStarposFinish())
			.maxStarpos(equipDto.getMaxStarpos())
			.changeCount(equipDto.getChangeCount())
			.isChange(equipDto.getIsChange())
			.upgradeCount(equipDto.getUpgradeCount())
			.job(equipDto.getJob())
			.equipType(equipDto.getEquipType())
			.userId(equipDto.getUserId())
			.stats(equipDto.getStats())
			.destroy(equipDto.isDestroy())
			.build();
						
		equipRepository.save(equip);
	}
}
