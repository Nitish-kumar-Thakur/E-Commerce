package com.ecommerce.repository;

import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.entities.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String Email);
    List<User> findByRole(Role role);
    List<User> findByStatus(UserStatus status);
}
