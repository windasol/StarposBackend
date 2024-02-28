package Starpos.starpos.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Starpos.starpos.dto.EtcInfoDto;
import Starpos.starpos.entity.EtcInfo;
import Starpos.starpos.repository.EtcInfoRepository;
import Starpos.starpos.service.EtcInfoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EtcInfoServiceImpl implements EtcInfoService {

	private final EtcInfoRepository etcInfoRepository;
	
	public List<EtcInfoDto> searchEtc(String userId) {
		return etcInfoRepository.findByUserId(userId).stream()
				.map(EtcInfo::toDto).collect(Collectors.toList());
	}
}
