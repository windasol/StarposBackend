package Starpos.starpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Starpos.starpos.entity.EquipInfo;

@Repository
public interface EquipRepository extends JpaRepository<EquipInfo, Long> {
	public List<EquipInfo> findByUserId(String userId);
}
