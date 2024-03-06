package Starpos.starpos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Starpos.starpos.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUserId(String userId);

}
