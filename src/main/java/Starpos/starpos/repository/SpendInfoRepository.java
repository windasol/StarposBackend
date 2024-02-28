package Starpos.starpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Starpos.starpos.entity.SpendInfo;

public interface SpendInfoRepository extends JpaRepository<SpendInfo, Integer> {
	public List<SpendInfo> findByUserId(String userId);

}
