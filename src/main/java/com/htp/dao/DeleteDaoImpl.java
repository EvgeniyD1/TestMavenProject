package com.htp.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteDaoImpl implements DeleteDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DeleteDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void deleteUser(Long id) {
        final String deleteUser = "update real_estate_agency.public.m_users mu set delete = true where id = :id";
        final String deleteRole = "update real_estate_agency.public.m_roles mr set delete = true where user_id = :id";
        final String deleteBuilding = "update m_buildings mb set delete = true where user_id = :id";
        final String deleteActivities =
                "update m_real_estate_activities mrea\n" +
                        "set delete = mu.delete\n" +
                        "from m_buildings mb\n" +
                        "         join m_users mu on mb.user_id = mu.id\n" +
                        "where building_id = mb.id\n" +
                        "  and mb.user_id = mu.id\n" +
                        "  and mu.id = :id";
        final String deleteActivitiesRequest =
                "update m_real_estate_activities_request mrear\n" +
                        "set delete = mu.delete\n" +
                        "from m_real_estate_activities mrea\n" +
                        "         join m_buildings mb on mrea.building_id = mb.id\n" +
                        "         join m_users mu on mb.user_id = mu.id\n" +
                        "where real_estate_activities_id = mrea.id\n" +
                        "  and mrea.building_id = mb.id\n" +
                        "  and mb.user_id = mu.id\n" +
                        "  and mu.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(deleteUser, parameterSource);
        namedParameterJdbcTemplate.update(deleteRole, parameterSource);
        namedParameterJdbcTemplate.update(deleteBuilding, parameterSource);
        namedParameterJdbcTemplate.update(deleteActivities, parameterSource);
        namedParameterJdbcTemplate.update(deleteActivitiesRequest, parameterSource);
    }

    @Override
    public void deleteRole(Long id) {
        final String deleteRole = "update real_estate_agency.public.m_roles mr set delete = true where id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(deleteRole, parameterSource);
    }

    @Override
    public void deleteBuilding(Long id) {
        final String deleteBuilding = "update m_buildings mb set delete = true where id = :id";
        final String deleteActivities =
                "update m_real_estate_activities mrea\n" +
                        "set delete = mb.delete\n" +
                        "from m_buildings mb\n" +
                        "where building_id = mb.id\n" +
                        "  and mb.id = :id";
        final String deleteActivitiesRequest =
                "update m_real_estate_activities_request mrear\n" +
                        "set delete = mb.delete\n" +
                        "from m_real_estate_activities mrea\n" +
                        "         join m_buildings mb on mrea.building_id = mb.id\n" +
                        "where real_estate_activities_id = mrea.id\n" +
                        "  and mrea.building_id = mb.id\n" +
                        "  and mb.id = :id;";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(deleteBuilding, parameterSource);
        namedParameterJdbcTemplate.update(deleteActivities, parameterSource);
        namedParameterJdbcTemplate.update(deleteActivitiesRequest, parameterSource);
    }

    @Override
    public void deleteActivities(Long id) {
        final String deleteActivities = "update m_real_estate_activities mrea set delete = true where id = :id";
        final String deleteActivitiesRequest = "update m_real_estate_activities_request mrear set delete = true " +
                "where real_estate_activities_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(deleteActivities, parameterSource);
        namedParameterJdbcTemplate.update(deleteActivitiesRequest, parameterSource);
    }

    @Override
    public void deleteActivitiesRequest(Long id) {
        final String deleteActivitiesRequest = "update m_real_estate_activities_request mrear set delete = true " +
                "where id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(deleteActivitiesRequest, parameterSource);
    }

    @Override
    public void restoreUser(Long id) {
        final String restoreUser = "update real_estate_agency.public.m_users mu set delete = false where id = :id";
        final String restoreRole = "update real_estate_agency.public.m_roles mr set delete = false where user_id = :id";
        final String restoreBuilding = "update m_buildings mb set delete = false where user_id = :id";
        final String restoreActivities =
                "update m_real_estate_activities mrea\n" +
                        "set delete = mu.delete\n" +
                        "from m_buildings mb\n" +
                        "         join m_users mu on mb.user_id = mu.id\n" +
                        "where building_id = mb.id\n" +
                        "  and mb.user_id = mu.id\n" +
                        "  and mu.id = :id";
        final String restoreActivitiesRequest =
                "update m_real_estate_activities_request mrear\n" +
                        "set delete = mu.delete\n" +
                        "from m_real_estate_activities mrea\n" +
                        "         join m_buildings mb on mrea.building_id = mb.id\n" +
                        "         join m_users mu on mb.user_id = mu.id\n" +
                        "where real_estate_activities_id = mrea.id\n" +
                        "  and mrea.building_id = mb.id\n" +
                        "  and mb.user_id = mu.id\n" +
                        "  and mu.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(restoreUser, parameterSource);
        namedParameterJdbcTemplate.update(restoreRole, parameterSource);
        namedParameterJdbcTemplate.update(restoreBuilding, parameterSource);
        namedParameterJdbcTemplate.update(restoreActivities, parameterSource);
        namedParameterJdbcTemplate.update(restoreActivitiesRequest, parameterSource);
    }

    @Override
    public void restoreRole(Long id) {
        final String restoreRole = "update real_estate_agency.public.m_roles mr set delete = false where id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(restoreRole, parameterSource);
    }

    @Override
    public void restoreBuilding(Long id) {
        final String restoreBuilding = "update m_buildings mb set delete = false where id = :id";
        final String restoreActivities =
                "update m_real_estate_activities mrea\n" +
                        "set delete = mb.delete\n" +
                        "from m_buildings mb\n" +
                        "where building_id = mb.id\n" +
                        "  and mb.id = :id";
        final String restoreActivitiesRequest =
                "update m_real_estate_activities_request mrear\n" +
                        "set delete = mb.delete\n" +
                        "from m_real_estate_activities mrea\n" +
                        "         join m_buildings mb on mrea.building_id = mb.id\n" +
                        "where real_estate_activities_id = mrea.id\n" +
                        "  and mrea.building_id = mb.id\n" +
                        "  and mb.id = :id;";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(restoreBuilding, parameterSource);
        namedParameterJdbcTemplate.update(restoreActivities, parameterSource);
        namedParameterJdbcTemplate.update(restoreActivitiesRequest, parameterSource);
    }

    @Override
    public void restoreActivities(Long id) {
        final String restoreActivities = "update m_real_estate_activities mrea set delete = false where id = :id";
        final String restoreActivitiesRequest = "update m_real_estate_activities_request mrear set delete = false " +
                "where real_estate_activities_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(restoreActivities, parameterSource);
        namedParameterJdbcTemplate.update(restoreActivitiesRequest, parameterSource);
    }

    @Override
    public void restoreActivitiesRequest(Long id) {
        final String restoreActivitiesRequest = "update m_real_estate_activities_request mrear set delete = false " +
                "where id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(restoreActivitiesRequest, parameterSource);
    }
}

