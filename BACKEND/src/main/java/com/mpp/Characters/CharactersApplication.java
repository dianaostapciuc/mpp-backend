package com.mpp.Characters;

import com.mpp.Characters.model.ApplicationUser;
import com.mpp.Characters.model.Role;
import com.mpp.Characters.repository.RoleRepository;
import com.mpp.Characters.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CharactersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharactersApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			System.out.println("Starting CharactersApplication...");

			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), "admin@admin.com", roles);
			userRepository.save(admin);
		};
	}

}
