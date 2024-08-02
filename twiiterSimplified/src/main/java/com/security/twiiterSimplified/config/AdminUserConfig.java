package com.security.twiiterSimplified.config;

import com.security.twiiterSimplified.entiities.Role;
import com.security.twiiterSimplified.entiities.User;
import com.security.twiiterSimplified.repository.RoleRepository;
import com.security.twiiterSimplified.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByUsername("ADMIN");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("ADMIN already exists");
                },
                () -> {
                    var user = new User();
                    user.setUsername("ADMIN");
                    user.setPassword(passwordEncoder.encode("123123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);

                }
        );
    }


}
