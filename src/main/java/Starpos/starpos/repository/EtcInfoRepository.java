package Starpos.starpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Starpos.starpos.entity.EtcInfo;

public interface EtcInfoRepository extends JpaRepository<EtcInfo, Integer>{
	public List<EtcInfo> findByUserId(String userId);
}
