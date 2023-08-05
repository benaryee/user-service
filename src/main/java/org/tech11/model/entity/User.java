package org.tech11.model.entity;

import jakarta.persistence.*;
import lombok.*;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "other_names")
    private String othernames;

    @CreationTimestamp
    @Column(name = "created")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;
}
