package com.ironhack.bankApi;

import com.ironhack.bankApi.models.users.Admin;
import com.ironhack.bankApi.repositories.AdminRepository;
import com.ironhack.bankApi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankApiApplication implements CommandLineRunner {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	RoleRepository roleRepository;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		adminRepository.deleteAll();
		roleRepository.deleteAll();
		Admin admin = adminRepository.save(new Admin("admin",passwordEncoder.encode("1234"), "Admin"));
		adminRepository.save(admin);
	}

}
