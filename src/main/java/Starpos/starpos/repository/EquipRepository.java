package Starpos.starpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Starpos.starpos.entity.EquipInfo;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public interface EquipRepository extends JpaRepository<EquipInfo, Integer> {

}
