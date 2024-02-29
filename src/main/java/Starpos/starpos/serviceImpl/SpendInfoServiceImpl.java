package Starpos.starpos.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Starpos.starpos.dto.SpendInfoDto;
import Starpos.starpos.entity.SpendInfo;
import Starpos.starpos.repository.SpendInfoRepository;
import Starpos.starpos.service.SpendInfoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SpendInfoServiceImpl implements SpendInfoService {
	private final SpendInfoRepository spendInfoRepository;
	
	public List<SpendInfoDto> searchSpend(String userId) {
		return spendInfoRepository.findByUserId(userId).stream()
				.map(SpendInfo::toDto).collect(Collectors.toList()) ;
	}
	
	public void upgradeSpend(SpendInfoDto dto) {
		SpendInfo spend = SpendInfo.builder()
				.seq(dto.getSeq())
				.type(dto.getType())
				.orders(dto.getOrders())
				.name(dto.getName())
				.description(dto.getDescription())
				.count(dto.getCount())
				.imgUrl(dto.getImgUrl())
				.userId(dto.getUserId())
				.build();
		
		spendInfoRepository.save(spend);
	}
}
