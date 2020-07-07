package com.htp.dao.jdbctemplate;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("userRepositoryJdbcTemplate")
public class UserRepository implements UserDao {

    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_SURNAME = "surname";
    public static final String USER_PATRONYMIC = "patronymic";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CREATED = "created";
    public static final String USER_CHANGED = "changed";
    public static final String USER_BIRTH_DATE = "birth_date";
    public static final String USER_IS_BLOCKED = "is_blocked";
    public static final String USER_MAIL = "mail";
    public static final String USER_COUNTRY_LOCATION = "country_location";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private User userRowMapper(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(USER_ID));
        user.setUsername(resultSet.getString(USER_USERNAME));
        user.setSurname(resultSet.getString(USER_SURNAME));
        user.setPatronymic(resultSet.getString(USER_PATRONYMIC));
        user.setPhoneNumber(resultSet.getString(USER_PHONE_NUMBER));
        user.setLogin(resultSet.getString(USER_LOGIN));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        user.setCreated(resultSet.getTimestamp(USER_CREATED));
        user.setChanged(resultSet.getTimestamp(USER_CHANGED));
        user.setBirthDate(resultSet.getDate(USER_BIRTH_DATE));
        user.setBlocked(resultSet.getBoolean(USER_IS_BLOCKED));
        user.setMail(resultSet.getString(USER_MAIL));
        user.setCountryLocation(resultSet.getString(USER_COUNTRY_LOCATION));
        return user;
    }

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_users order by id desc";
        return jdbcTemplate.query(findAllQuery, this::userRowMapper);
    }

    @Override
    public List<User> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_users where login = :login order by id desc";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("login", searchParam);
        return namedParameterJdbcTemplate.query(findAllQueryForPrepared,parameterSource,this::userRowMapper);
    }

    @Override
    public User findByLogin(String searchParam) {
        final String findByLogin = "select * from m_users where login = :login";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("login", searchParam);
        return namedParameterJdbcTemplate.queryForObject(findByLogin,parameterSource,this::userRowMapper);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(findOne(userId));
    }

    @Override
    public User findOne(Long userId) {
        final String findById = "select * from m_users where id = :userId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userId", userId);
        return  namedParameterJdbcTemplate.queryForObject(findById,parameterSource,this::userRowMapper);
    }

    @Override
    public User save(User user) {
        final String insertQuery = "insert into m_users (username, surname, patronymic, phone_number, login, " +
                "password, created, changed, birth_date, is_blocked, mail, country_location)" +
                "VALUES (:username, :surname, :patronymic, :phone_number, :login, :password, :created, :changed, " +
                ":birth_date, :is_blocked, :mail, :country_location)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("username",user.getUsername())
                .addValue("surname",user.getSurname())
                .addValue("patronymic",user.getPatronymic())
                .addValue("phone_number",user.getPhoneNumber())
                .addValue("login",user.getLogin())
                .addValue("password",user.getPassword())
                .addValue("created",user.getCreated())
                .addValue("changed",user.getChanged())
                .addValue("birth_date",user.getBirthDate())
                .addValue("is_blocked",user.isBlocked())
                .addValue("mail",user.getMail())
                .addValue("country_location",user.getCountryLocation());
        namedParameterJdbcTemplate.update(insertQuery,parameterSource,keyHolder,new String[]{"id"});
        long newUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findOne(newUserId);
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_users set username = :username, surname = :surname, " +
                "patronymic = :patronymic, phone_number = :phone_number, login = :login, " +
                "password = :password, created = :created, changed = :changed, birth_date = :birth_date, " +
                "is_blocked = :is_blocked, mail = :mail, country_location = :country_location where id = :userId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("username",user.getUsername())
                .addValue("surname",user.getSurname())
                .addValue("patronymic",user.getPatronymic())
                .addValue("phone_number",user.getPhoneNumber())
                .addValue("login",user.getLogin())
                .addValue("password",user.getPassword())
                .addValue("created",user.getCreated())
                .addValue("changed",user.getChanged())
                .addValue("birth_date",user.getBirthDate())
                .addValue("is_blocked",user.isBlocked())
                .addValue("mail",user.getMail())
                .addValue("country_location",user.getCountryLocation())
                .addValue("userId", user.getId());
        namedParameterJdbcTemplate.update(updateQuery, parameterSource);
        return findOne(user.getId());
    }

    @Override
    public int delete(User user) {
        final String deleteQuery ="delete from m_users where id = :userId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userId", user.getId());
        return namedParameterJdbcTemplate.update(deleteQuery,parameterSource);
    }

    @Override
    public List<User> batchUpdate(List<User> users) {
        final String updateQuery = "update m_users set username = :username, surname = :surname, " +
                "patronymic = :patronymic, phone_number = :phone_number, login = :login, " +
                "password = :password, created = :created, changed = :changed, birth_date = :birth_date, " +
                "is_blocked = :is_blocked, mail = :mail, country_location = :country_location" +
                "where id = :userId";
        List<SqlParameterSource> batch = new ArrayList<>();
        for (User user : users) {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("username",user.getUsername())
                    .addValue("surname",user.getSurname())
                    .addValue("patronymic",user.getPatronymic())
                    .addValue("phone_number",user.getPhoneNumber())
                    .addValue("login",user.getLogin())
                    .addValue("password",user.getPassword())
                    .addValue("created",user.getCreated())
                    .addValue("changed",user.getChanged())
                    .addValue("birth_date",user.getBirthDate())
                    .addValue("is_blocked",user.isBlocked())
                    .addValue("mail",user.getMail())
                    .addValue("country_location",user.getCountryLocation())
                    .addValue("userId", user.getId());
            batch.add(parameterSource);
        }
        namedParameterJdbcTemplate.batchUpdate(updateQuery,batch.toArray(new SqlParameterSource[batch.size()]));
        return users;
    }

}
