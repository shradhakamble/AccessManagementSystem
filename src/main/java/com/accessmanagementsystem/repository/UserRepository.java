package com.accessmanagementsystem.repository;

import com.accessmanagementsystem.repository.entity.DBUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DBUsers, Integer> {
    DBUsers findByUserName(String username);

    @Query("FROM DBUsers c WHERE c.userName = ?1 OR c.phoneNumber = ?1")
    DBUsers findByUserNameOrPhone(String usernameOrPhone);
}
