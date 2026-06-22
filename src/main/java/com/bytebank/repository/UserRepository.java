package com.bytebank.repository;

import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
=======

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // TODO: add custom query methods as needed
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
