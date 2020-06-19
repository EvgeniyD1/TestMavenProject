package com.htp.zhomework;

import java.util.List;
import java.util.Objects;

public class UserUser {
    private Long id;
    private String name;
    private String surname;
    private List<UserUser> friends;

    public UserUser() {
    }

    public UserUser(Long id, String name, String surname, List<UserUser> friends) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.friends = friends;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<UserUser> getFriends() {
        return friends;
    }

    public void setFriends(List<UserUser> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUser userUser = (UserUser) o;
        return Objects.equals(id, userUser.id) &&
                Objects.equals(name, userUser.name) &&
                Objects.equals(surname, userUser.surname) &&
                Objects.equals(friends, userUser.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, friends);
    }

    @Override
    public String toString() {
        return "UserUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", friends=" + friends +
                '}';
    }
}

