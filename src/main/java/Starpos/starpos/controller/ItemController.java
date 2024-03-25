package Starpos.starpos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Starpos.starpos.dto.CashInfoDto;
import Starpos.starpos.dto.EquipInfoDto;
import Starpos.starpos.dto.EtcInfoDto;
import Starpos.starpos.dto.SpendInfoDto;
import Starpos.starpos.serviceImpl.CashInfoServiceImpl;
import Starpos.starpos.serviceImpl.EquipInfoServiceImpl;
import Starpos.starpos.serviceImpl.EtcInfoServiceImpl;
import Starpos.starpos.serviceImpl.SpendInfoServiceImpl;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
	
	
	private final EquipInfoServiceImpl equipInfoServiceImpl;
	private final SpendInfoServiceImpl spendServiceImpl;
	private final EtcInfoServiceImpl etcInfoServiceImpl;
	private final CashInfoServiceImpl cashInfoServiceImpl;
			
	@GetMapping("/search-equip")
	public List<EquipInfoDto> searchEquip(@RequestParam(value= "userId") String userId) {			
			
		return equipInfoServiceImpl.searchEquip(userId);		
	}
	
	@GetMapping("/search-spend")
	public List<SpendInfoDto> searchSpend(@RequestParam(value= "userId") String userId) {
		return spendServiceImpl.searchSpend(userId);
		
		
	}
	
	@GetMapping("/search-etc")
	public List<EtcInfoDto> searchEtc(@RequestParam(value= "userId") String userId) {
		
		return etcInfoServiceImpl.searchEtc(userId);
	}
	
	@GetMapping("/search-cash")
	public List<CashInfoDto> searchCash(@RequestParam(value= "userId") String userId) {
		
		return cashInfoServiceImpl.searchCash(userId);
	}
	
	@PostMapping("/upgrade-equip")
	public void upgradeEquip(@RequestBody EquipInfoDto dto) {		
		equipInfoServiceImpl.upgradeEquip(dto);
	}
	
	@PostMapping("/upgrade-spend")
	public void upgradeSpend(@RequestBody SpendInfoDto dto) {		
		spendServiceImpl.upgradeSpend(dto);
	}
	
	@PostMapping("/upgrade-etc")
	public void upgradeEtc(@RequestBody EtcInfoDto dto) {		
		etcInfoServiceImpl.updateEtc(dto);
	}
	
	@PostMapping("/upgrade-cash")
	public void upgradeCash(@RequestBody CashInfoDto dto) {		
		cashInfoServiceImpl.updateCash(dto);
	}
	
	
	
	
}
