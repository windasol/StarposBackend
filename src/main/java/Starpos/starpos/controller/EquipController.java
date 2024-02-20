package Starpos.starpos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Starpos.starpos.entity.EquipInfo;
import Starpos.starpos.service.EquipService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class EquipController {

	private final EquipService equipService;
	
	public EquipController(EquipService equipService) {
		this.equipService = equipService;
	}
	
	@GetMapping("/test")
	public List<EquipInfo> test() {		
		List<EquipInfo> list = equipService.searchEquip();
		System.out.println(list);
		
		for (EquipInfo item : list) {
		    System.out.println(item);
		}
		
		return list;
	}
}
