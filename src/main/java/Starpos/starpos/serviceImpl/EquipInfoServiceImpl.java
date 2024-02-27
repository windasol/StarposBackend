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
		return equipRepository.findByUserid(userId).stream().map(EquipInfo::toDto).collect(Collectors.toList());		
	}
}
