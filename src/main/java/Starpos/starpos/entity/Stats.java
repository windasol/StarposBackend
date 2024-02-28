package Starpos.starpos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
	
	@Id
	private int itemSeq;
	private int str;
	private int dex;
	private int ints;
	private int luk;
	private int power;
	private int max_hp;
	private int max_mp;
	private int all_stat;
	private int magic_power;
	private int boss_power;
	private int guard_ignore;		
}
