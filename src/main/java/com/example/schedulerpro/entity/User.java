package com.example.schedulerpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column( nullable = false, length = 20)
    private String user_name;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    public User() {
    }

    public User(String userName, String password, String email) {
        this.password = password;
        this.email = email;
        this.user_name = userName;
    }

    public void update(String password, String user_name, String email) {
        this.password = password;
        this.user_name = user_name;
        this.email = email;
    }

}
