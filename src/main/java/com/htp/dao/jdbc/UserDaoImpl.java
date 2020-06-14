package com.htp.dao.jdbc;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;
import static com.htp.util.DatabaseConfiguration.DATABASE_PASSWORD;

@Repository("userRepositoryJdbc")
public class UserDaoImpl implements UserDao {

    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();

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

    String driverName = config.getProperty(DATABASE_DRIVER_NAME);
    String url = config.getProperty(DATABASE_URL);
    String login = config.getProperty(DATABASE_LOGIN);
    String databasePassword = config.getProperty(DATABASE_PASSWORD);

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_users order by id desc";
        List<User> resultList = new ArrayList<>();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery();
             ) {
            while (resultSet.next()) {
                resultList.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    @Override
    public List<User> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_users where id > ? order by id desc";
        List<User> resultList = new ArrayList<>();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQueryForPrepared)) {
            preparedStatement.setLong(1, Long.parseLong(searchParam));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(findOne(userId));
    }

    @Override
    public User findOne(Long userId) {
        final String findById = "select * from m_users where id = ?";
        User user = null;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findById);
        ) {
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = parseResultSet(resultSet);
            } else {
                throw new ResourceNotFoundException("User with id " + userId + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User save(User user) {
        final String insertQuery = "insert into m_users (username, surname, patronymic, phone_number, login, " +
                "password, created, changed, birth_date, is_blocked, mail, country_location)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String lastInsertId = "SELECT currval('m_users_id_seq') as last_insert_id";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        ResultSet set = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             PreparedStatement preparedStatementLastInsertId = connection.prepareStatement(lastInsertId)
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setTimestamp(7, user.getCreated());
            preparedStatement.setTimestamp(8, user.getChanged());
            preparedStatement.setDate(9, user.getBirthDate());
            preparedStatement.setBoolean(10, user.isBlocked());
            preparedStatement.setString(11, user.getMail());
            preparedStatement.setString(12, user.getCountryLocation());
            preparedStatement.executeUpdate();
            set = preparedStatementLastInsertId.executeQuery();
            set.next();
            long insertedUserId = set.getInt("last_insert_id");
            return findOne(insertedUserId);
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
        finally {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_users set username = ?, surname = ?, patronymic = ?, phone_number = ?, login = ?, " +
                "password = ?, created = ?, changed = ?, birth_date = ?, is_blocked = ?, mail = ?, country_location = ?" +
                "where id = ?";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setTimestamp(7, user.getCreated());
            preparedStatement.setTimestamp(8, user.getChanged());
            preparedStatement.setDate(9, user.getBirthDate());
            preparedStatement.setBoolean(10, user.isBlocked());
            preparedStatement.setString(11, user.getMail());
            preparedStatement.setString(12, user.getCountryLocation());
            preparedStatement.setLong(13, user.getId());
            preparedStatement.executeUpdate();
            return findOne(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public int delete(User user) {
        final String deleteQuery ="delete from m_users where id = ?";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)
        ) {
            preparedStatement.setLong(1, user.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public List<User> batchUpdate(List<User> users) {
        final String updateQuery = "update m_users set username = ?, surname = ?, patronymic = ?, phone_number = ?, login = ?, " +
                "password = ?, created = ?, changed = ?, birth_date = ?, is_blocked = ?, mail = ?, country_location = ?" +
                "where id = ?";
        List<User> resultList = new ArrayList<>();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            connection.setAutoCommit(false);
            for (User user : users) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getPatronymic());
                preparedStatement.setString(4, user.getPhoneNumber());
                preparedStatement.setString(5, user.getLogin());
                preparedStatement.setString(6, user.getPassword());
                preparedStatement.setTimestamp(7, user.getCreated());
                preparedStatement.setTimestamp(8, user.getChanged());
                preparedStatement.setDate(9, user.getBirthDate());
                preparedStatement.setBoolean(10, user.isBlocked());
                preparedStatement.setString(11, user.getMail());
                preparedStatement.setString(12, user.getCountryLocation());
                preparedStatement.setLong(13, user.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
        return resultList;
    }

    private User parseResultSet(ResultSet resultSet) throws SQLException {
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
}
