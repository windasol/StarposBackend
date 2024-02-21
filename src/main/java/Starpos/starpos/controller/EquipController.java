package Starpos.starpos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Starpos.starpos.dto.EquipInfoDto;
import Starpos.starpos.service.EquipService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EquipController {
	private final EquipService equipService;
			
	@GetMapping("/search")
	public List<EquipInfoDto> searchEquip(@RequestParam(value= "userId") String userId) {				
		return equipService.searchEquip("admin");
	}
}
