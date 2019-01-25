package com.jazasoft.mtdb.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User extends AbstractUser {

    public User() {
    }

    public User(String name, String email, String mobile, String username, String roles) {
        super(name, email, mobile, username, roles);
    }
}
