package Starpos.starpos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipController {

	@GetMapping("/test")
	public String test() {
		System.out.println("ok");
		return "ok";
	}
}
