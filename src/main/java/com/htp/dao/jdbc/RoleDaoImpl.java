package com.htp.dao.jdbc;

import com.htp.dao.RoleDAO;
import com.htp.domain.Role;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;
import static com.htp.util.DatabaseConfiguration.DATABASE_PASSWORD;

@Repository("roleRepositoryJdbc")
public class RoleDaoImpl implements RoleDAO {

    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();
    String driverName = config.getProperty(DATABASE_DRIVER_NAME);
    String url = config.getProperty(DATABASE_URL);
    String login = config.getProperty(DATABASE_LOGIN);
    String databasePassword = config.getProperty(DATABASE_PASSWORD);

    public static final String ROLE_ID = "id";
    public static final String ROLE_ROLE_NAME = "role_name";
    public static final String ROLE_USER_ID = "user_id";

    @Override
    public List<Role> findAll() {
        final String findAllQuery = "select * from m_roles order by id desc";
        List<Role> resultList = new ArrayList<>();
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
    public List<Role> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_roles where id > ? order by id desc";
        List<Role> resultList = new ArrayList<>();
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
    public Optional<Role> findById(Long roleId) {
        return Optional.ofNullable(findOne(roleId));
    }

    @Override
    public Role findOne(Long roleId) {
        final String findById = "select * from m_roles where id = ?";
        Role role = null;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findById);
        ) {
            preparedStatement.setLong(1,roleId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = parseResultSet(resultSet);
            } else {
                throw new ResourceNotFoundException("Role with id " + roleId + " not found");
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
        return role;
    }

    @Override
    public Role save(Role role) {
        final String insertQuery = "insert into m_roles (role_name, user_id) VALUES (?, ?)";
        final String lastInsertId = "SELECT currval('m_roles_id_seq') as last_insert_id";
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
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setLong(2, role.getUserId());
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
    public Role update(Role role) {
        final String updateQuery = "update m_roles set role_name = ?, user_id = ? where id = ?";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setLong(2, role.getUserId());
            preparedStatement.setLong(3, role.getId());
            preparedStatement.executeUpdate();
            return findOne(role.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public int delete(Role role) {
        /*смысла нет, позже удалить*/
        final String deleteQuery ="delete from m_roles where id = ?";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)
        ) {
            preparedStatement.setLong(1, role.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public List<Role> batchUpdate(List<Role> roles) {
        /*смысла нет, позже удалить*/
        final String updateQuery = "update m_roles set role_name = ?, user_id = ? where id = ?";
        List<Role> resultList = new ArrayList<>();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)
        ) {
            connection.setAutoCommit(false);
            for (Role role : roles) {
                preparedStatement.setString(1, role.getRoleName());
                preparedStatement.setLong(2, role.getUserId());
                preparedStatement.setLong(3, role.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
        return resultList;
    }

    private Role parseResultSet(ResultSet resultSet) throws SQLException{
        Role role = new Role();
        role.setId(resultSet.getLong(ROLE_ID));
        role.setRoleName(resultSet.getString(ROLE_ROLE_NAME));
        role.setUserId(resultSet.getLong(ROLE_USER_ID));
        return role;
    }
}
