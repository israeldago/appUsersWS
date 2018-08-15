package com.israeldago.appUsersWS.repository;

import com.israeldago.appUsersWS.entities.db.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserDB, Integer>{
    Optional<UserDB> findByUsername(String username);
    Optional<UserDB> findByIdentityCardNumber(String identityCardNumber);
}
