package com.awstest.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "login")
    @NonNull
    private String login;

    @Column(name = "password")
    @NonNull
    private String password;
}
