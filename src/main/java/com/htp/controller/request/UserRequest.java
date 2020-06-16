package com.htp.controller.request;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class UserRequest {

    private Long id;
    private String username;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String login;
    private String password;
    private Timestamp created;
    private Timestamp changed;
    private Date birthDate;
    private boolean isBlocked;
    private String mail;
    private String countryLocation;

    public UserRequest() {
    }

    public UserRequest(Long id, String username, String surname, String patronymic,
                       String phoneNumber, String login, String password, Timestamp created,
                       Timestamp changed, Date birthDate, boolean isBlocked, String mail,
                       String countryLocation) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.created = created;
        this.changed = changed;
        this.birthDate = birthDate;
        this.isBlocked = isBlocked;
        this.mail = mail;
        this.countryLocation = countryLocation;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCountryLocation() {
        return countryLocation;
    }

    public void setCountryLocation(String countryLocation) {
        this.countryLocation = countryLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return isBlocked == that.isBlocked &&
                Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(created, that.created) &&
                Objects.equals(changed, that.changed) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(countryLocation, that.countryLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, surname, patronymic, phoneNumber, login, password, created, changed,
                birthDate, isBlocked, mail, countryLocation);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", birthDate=" + birthDate +
                ", isBlocked=" + isBlocked +
                ", mail='" + mail + '\'' +
                ", countryLocation='" + countryLocation + '\'' +
                '}';
    }
}
