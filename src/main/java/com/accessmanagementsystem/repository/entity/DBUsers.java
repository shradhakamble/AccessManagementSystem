package com.accessmanagementsystem.repository.entity;

import com.accessmanagementsystem.dto.users.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_TBL")
@NoArgsConstructor
public class DBUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName; // Organization ID and Username are stored in the same column
    private String password;
    private String email;
    private String permissions;
    private UserType userType;
    private Long numberOfSessions;
    private String phoneNumber;

    public DBUsers(String userName,
                   String password,
                   String email,
                   String permissions,
                   UserType userType,
                   String phoneNumber) {
        this.userType = userType;
        this.userName = userName;
        this.email = email;
        this.permissions = permissions;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public DBUsers(DBUsers user) {
        this.id = user.id;
        this.userName = user.userName;
        this.password = user.password;
        this.email = user.email;
        this.permissions = user.permissions;
        this.userType = user.userType;
        this.numberOfSessions = user.numberOfSessions;
        this.phoneNumber = user.phoneNumber;
    }
}
