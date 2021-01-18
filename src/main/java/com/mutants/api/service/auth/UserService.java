package com.mutants.api.service.auth;

import com.mutants.api.domain.auth.Role;
import com.mutants.api.domain.auth.User;
import com.mutants.api.util.PBKDF2Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

  // this is just an example, you can load the user from the database from the repository

  private Map<String, User> data;

  @Autowired private PBKDF2Encoder encoder;

  @PostConstruct
  public void init() {
    data = new HashMap<>();

    // username:passwowrd -> user:user
    data.put("user", new User("user", encoder.encode("user"), true, Arrays.asList(Role.ROLE_USER)));

    // username:passwowrd -> admin:admin
    data.put(
        "admin", new User("admin", encoder.encode("admin"), true, Arrays.asList(Role.ROLE_ADMIN)));

    // username:passwowrd -> magneto:magneto
    data.put(
        "magneto",
        new User("magneto", encoder.encode("magneto"), true, Arrays.asList(Role.ROLE_MAGNETO)));
  }

  public Mono<User> findByUsername(String username) {
    if (data.containsKey(username)) {
      return Mono.just(data.get(username));
    } else {
      return Mono.empty();
    }
  }
}
