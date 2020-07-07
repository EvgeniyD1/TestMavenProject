package com.htp.dao.jdbctemplate;

import com.htp.dao.RoleDao;
import com.htp.domain.Role;
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

@Repository("roleRepositoryJdbcTemplate")
public class RoleRepository implements RoleDao {

    public static final String ROLE_ID = "id";
    public static final String ROLE_ROLE_NAME = "role_name";
    public static final String ROLE_USER_ID = "user_id";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Role roleRowMapper(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(ROLE_ID));
        role.setRoleName(resultSet.getString(ROLE_ROLE_NAME));
        role.setUserId(resultSet.getLong(ROLE_USER_ID));
        return role;
    }

    @Override
    public List<Role> findAll() {
        final String findAllQuery = "select * from m_roles order by id desc";
        return jdbcTemplate.query(findAllQuery, this::roleRowMapper);
    }

    @Override
    public List<Role> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_roles where role_name = :role_name order by id desc";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("role_name", searchParam);
        return namedParameterJdbcTemplate.query(findAllQueryForPrepared,parameterSource,this::roleRowMapper);
    }

    @Override
    public Optional<Role> findById(Long roleId) {
        return Optional.ofNullable(findOne(roleId));
    }

    @Override
    public Role findOne(Long roleId) {
        final String findById = "select * from m_roles where id = :roleId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("roleId", roleId);
        return  namedParameterJdbcTemplate.queryForObject(findById,parameterSource,this::roleRowMapper);
    }

    @Override
    public List<Role> findAllRole(Long userId) {
        final String findAllRole = "select * from m_roles where user_id = :userId order by role_name";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userId", userId);
        return namedParameterJdbcTemplate.query(findAllRole,parameterSource,this::roleRowMapper);
    }

    @Override
    public Role save(Role role) {
        final String insertQuery = "insert into m_roles (role_name, user_id) VALUES (:role_name, :userId)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("role_name",role.getRoleName())
                .addValue("userId",role.getUserId());
        namedParameterJdbcTemplate.update(insertQuery,parameterSource,keyHolder,new String[]{"id"});
        long newRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findOne(newRoleId);
    }

    @Override
    public Role update(Role role) {
        final String updateQuery = "update m_roles set role_name = :role_name, user_id = :userId where id = :roleId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("role_name",role.getRoleName())
                .addValue("userId",role.getUserId())
                .addValue("roleId",role.getId());
        namedParameterJdbcTemplate.update(updateQuery, parameterSource);
        return findOne(role.getId());
    }

    @Override
    public int delete(Role role) {
        final String deleteQuery ="delete from m_roles where id = :roleId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("roleId", role.getId());
        return namedParameterJdbcTemplate.update(deleteQuery,parameterSource);
    }

    @Override
    public List<Role> batchUpdate(List<Role> roles) {
        final String updateQuery = "update m_roles set role_name = :role_name, user_id = :userId where id = :roleId";
        List<SqlParameterSource> batch = new ArrayList<>();
        for (Role role : roles) {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("role_name",role.getRoleName())
                    .addValue("userId",role.getUserId())
                    .addValue("roleId",role.getId());
            batch.add(parameterSource);
        }
        namedParameterJdbcTemplate.batchUpdate(updateQuery,batch.toArray(new SqlParameterSource[batch.size()]));
        return roles;
    }
}
