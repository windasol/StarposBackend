package Starpos.starpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Starpos.starpos.entity.EquipInfo;
import Starpos.starpos.repository.EquipRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipService {

	private final EquipRepository equipRepository;
	
	public EquipService(EquipRepository equipRepository) {
		this.equipRepository = equipRepository;
	}
	
	public List<EquipInfo> searchEquip() {		
		return equipRepository.findAll();
	}
}
