package com.example.myongsick.domain.user.repository;

import com.example.myongsick.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByPhoneId(String phoneId);
}
