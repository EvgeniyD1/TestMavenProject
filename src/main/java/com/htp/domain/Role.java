package com.htp.domain;

import java.util.Objects;

public class Role {
    private Long id;
    private String roleName;
    private Long userId;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(userId, role.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, userId);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
