package com.accessmanagementsystem;

import com.accessmanagementsystem.dto.users.UserType;
import com.accessmanagementsystem.repository.UserRepository;
import com.accessmanagementsystem.repository.entity.DBUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AccessManagementService {

    @Autowired
    private UserRepository repository;

    /**
     * Initialize our DB with some dummy data
     */
    @PostConstruct
    public void initUsers() {
        //orgID of Service User is handled under username field while storing in the db
        List<DBUsers> users = new ArrayList<DBUsers>() {{
            add(new DBUsers(
                    "username",                          // username
                    "password",                         // Password
                    "email",      // Email
                    "superadmin",                       // permission / role type
                    UserType.CUSTOMER,                  // User Type
                    "phonenumber"                     // phone number
            ));
            add(new DBUsers(
                    "ams1",                             // organizationID
                    "ams1@123",                         // Password
                    "ams1@gmail.com",                   // Email
                    "basic",                            // permission / role type
                    UserType.SERVICE_USER,              // User Type
                    ""                                  // phone number
            ));
            add(new DBUsers(
                "test",                             // organizationID
                "test@1234",                         // Password
                "test@gmail.com",                   // Email
                "basic",                            // permission / role type
                UserType.SERVICE_USER,              // User Type
                ""                                  // phone number
            ));
        }};
        repository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(AccessManagementService.class, args);
    }

}
