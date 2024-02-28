package Starpos.starpos.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Starpos.starpos.dto.CashInfoDto;
import Starpos.starpos.entity.CashInfo;
import Starpos.starpos.repository.CashInfoRepository;
import Starpos.starpos.service.CashInfoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CashInfoServiceImpl implements CashInfoService {
	private final CashInfoRepository cashInfoRepository;
	
	public List<CashInfoDto> searchCash(String userId) {
		return cashInfoRepository.findByUserId(userId).stream()
				.map(CashInfo::toDto).collect(Collectors.toList());
	}

}
