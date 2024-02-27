package Starpos.starpos.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import Starpos.starpos.entity.SpendInfo;
import Starpos.starpos.repository.SpendInfoRepository;
import Starpos.starpos.service.SpendInfoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SpendInfoServiceImpl implements SpendInfoService {
	private final SpendInfoRepository spendInfoRepository;
	
	public List<SpendInfo> searchSpend() {
		return spendInfoRepository.findAll();
	}
}
