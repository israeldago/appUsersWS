package com.israeldago.appUsersWS.repository;

import com.israeldago.appUsersWS.entities.db.RoleDB;
import com.israeldago.appUsersWS.entities.db.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RolesRepository extends JpaRepository<RoleDB, Integer>{
    List<RoleDB> findByUser(UserDB userDB);
}
