package Starpos.starpos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Starpos.starpos.dto.EquipInfoDto;
import Starpos.starpos.entity.SpendInfo;
import Starpos.starpos.service.EquipService;
import Starpos.starpos.serviceImpl.EquipInfoServiceImpl;
import Starpos.starpos.serviceImpl.SpendInfoServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
	
	private final EquipInfoServiceImpl equipInfoServiceImpl;
	private final SpendInfoServiceImpl spendServiceImpl;
			
	@GetMapping("/searchEquip")
	public List<EquipInfoDto> searchEquip(@RequestParam(value= "userId") String userId) {				
		return equipInfoServiceImpl.searchEquip("admin");		
	}
	
	@GetMapping("/searchSpend")
	public List<SpendInfo> searchSpend(@RequestParam(value= "userId") String param) {
		return spendServiceImpl.searchSpend();
	}
	
}
