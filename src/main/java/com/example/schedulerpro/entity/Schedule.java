package com.example.schedulerpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false,columnDefinition = "longtext")
    private String content;

    @Column(nullable = false, length = 20)
    private String user_name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Schedule() {
    }
    public Schedule(String title, String content, User user, String user_name) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.user_name = user_name;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
