package org.example.demo1.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;

    @Column(name = "c_username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "c_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_role", nullable = false, length = 20)
    private Role role;

    @Column(name = "c_profile_id")
    private Long profileId;

    public UserEntity() {
    }

    public UserEntity(
            Long id,
            Long profileId,
            String username,
            String password,
            Role role
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.profileId = profileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}