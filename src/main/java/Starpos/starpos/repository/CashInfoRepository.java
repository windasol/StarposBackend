package Starpos.starpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Starpos.starpos.entity.CashInfo;

public interface CashInfoRepository extends JpaRepository<CashInfo, Integer> {
	public List<CashInfo> findByUserId(String userId);
}
