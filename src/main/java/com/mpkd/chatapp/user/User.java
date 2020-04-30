package com.mpkd.chatapp.user;

import com.mpkd.chatapp.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "USERS", indexes = {@Index(columnList = "id", unique = true)})
@SequenceGenerator(name = "ID_GEN", sequenceName = "USERS_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
