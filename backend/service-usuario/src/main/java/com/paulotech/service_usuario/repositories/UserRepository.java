package com.paulotech.service_usuario.repositories;

import com.paulotech.service_usuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
