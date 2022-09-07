package com.alkemy.ong.infrastructure.config.seeder;

import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IRoleSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@Profile("default")
class UserSeeder implements CommandLineRunner {

  private static final List<String> ADMIN_NAMES = List.of("John Von", "Alan", "Linus", "Stephen",
      "Richard");
  private static final List<String> ADMIN_LAST_NAME = List.of("Neumann", "Turing", "Torvalds",
      "Hawking", "Stallman");
  private static final List<String> ADMIN_EMAILS = List.of("johnvon@neumann.com", "alan@turing.com",
      "sudo@torvalds.com", "stephen@hawking.com", "richard@stallman.com");
  private static final List<String> NAMES_USER = List.of("Robert", "Martin", "Kent", "Edsger",
      "Mary");
  private static final List<String> LAST_NAMES_USER = List.of("Martin", "Fowler", "Beck",
      "Dijkstra", "Shaw");
  private static final List<String> EMAILS_USER = List.of("uncle@bob.com", "fowler@martin.com",
      "kent@beck.com", "edsger@dijkstra.com", "mary@shaw.com");

  private static final String PASSWORD = "abcd1234";

  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Autowired
  protected IUserSpringRepository userRepository;

  @Autowired
  protected IRoleSpringRepository roleRepository;

  @Override
  public void run(String... args) {
    seedUserTable();
  }

  private void seedUserTable() {
    if (userRepository.count() == 0) {
      createRoles();
      createAdminsUsers();
      createStandardUsers();
    }
  }

  private void createRoles() {
    if (roleRepository.count() == 0) {
      roleRepository.saveAll(List.of(
          buildRole(Role.USER),
          buildRole(Role.ADMIN)));
      log.info("Initial roles created.");
    }
  }

  private void createAdminsUsers() {
    Role roleAdmin = Role.ADMIN;
    for (int i = 0; i < 5; i++) {
      buildUser(ADMIN_NAMES.get(i),
          ADMIN_LAST_NAME.get(i),
          ADMIN_EMAILS.get(i),
          roleAdmin);
    }
    log.info("Initial admins created.");
  }

  private void createStandardUsers() {
    Role roleUser = Role.USER;
    for (int i = 0; i < 5; i++) {
      buildUser(NAMES_USER.get(i),
          LAST_NAMES_USER.get(i),
          EMAILS_USER.get(i),
          roleUser);
      log.info("Initial users created.");
    }
  }

  private RoleEntity buildRole(Role role) {
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setDescription(role.name());
    roleEntity.setName(role.getFullRoleName());
    return roleEntity;
  }

  private void buildUser(String firstName, String lastName, String email, Role role) {
    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(firstName);
    userEntity.setLastName(lastName);
    userEntity.setEmail(email);
    userEntity.setPassword(passwordEncoder.encode(PASSWORD));
    userEntity.setImageUrl("image.jpg");
    userEntity.setRole(roleRepository.findByName(role.getFullRoleName()));
    userRepository.save(userEntity);
  }
}