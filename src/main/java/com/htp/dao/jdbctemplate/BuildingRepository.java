package com.htp.dao.jdbctemplate;

import com.htp.dao.BuildingDao;
import com.htp.domain.Building;
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

@Repository("buildingRepositoryJdbcTemplate")
public class BuildingRepository implements BuildingDao {

    public static final String BUILDING_ID = "id";
    public static final String BUILDING_TYPE = "type";
    public static final String BUILDING_LAND_AREA = "land_area";
    public static final String BUILDING_ROOMS_COUNT = "rooms_count";
    public static final String BUILDING_TOTAL_ROOMS_AREA = "total_rooms_area";
    public static final String BUILDING_LIVING_AREA = "living_area";
    public static final String BUILDING_KITCHEN_AREA = "kitchen_area";
    public static final String BUILDING_BUILDING_FLOORS = "building_floors";
    public static final String BUILDING_FLOOR = "floor";
    public static final String BUILDING_BUILDING_YEAR = "building_year";
    public static final String BUILDING_REPAIRS = "repairs";
    public static final String BUILDING_GARAGE = "garage";
    public static final String BUILDING_BARN = "barn";
    public static final String BUILDING_BATH = "bath";
    public static final String BUILDING_DESCRIPTION = "description";
    public static final String BUILDING_COUNTRY_LOCATION = "country_location";
    public static final String BUILDING_REGION_LOCATION = "region_location";
    public static final String BUILDING_TOWN_LOCATION = "town_location";
    public static final String BUILDING_STREET_LOCATION = "street_location";
    public static final String BUILDING_BUILDING_LOCATION = "building_location";
    public static final String BUILDING_ROOM_LOCATION = "room_location";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BuildingRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Building buildingRowMapper(ResultSet resultSet, int i) throws SQLException {
        Building building = new Building();
        building.setId(resultSet.getLong(BUILDING_ID));
        building.setType(resultSet.getString(BUILDING_TYPE));
        building.setLandArea(resultSet.getInt(BUILDING_LAND_AREA));
        building.setRoomsCount(resultSet.getInt(BUILDING_ROOMS_COUNT));
        building.setTotalRoomsArea(resultSet.getInt(BUILDING_TOTAL_ROOMS_AREA));
        building.setLivingArea(resultSet.getInt(BUILDING_LIVING_AREA));
        building.setKitchenArea(resultSet.getInt(BUILDING_KITCHEN_AREA));
        building.setBuildingFloors(resultSet.getInt(BUILDING_BUILDING_FLOORS));
        building.setFloor(resultSet.getInt(BUILDING_FLOOR));
        building.setBuildingYear(resultSet.getInt(BUILDING_BUILDING_YEAR));
        building.setRepairs(resultSet.getBoolean(BUILDING_REPAIRS));
        building.setGarage(resultSet.getBoolean(BUILDING_GARAGE));
        building.setBarn(resultSet.getBoolean(BUILDING_BARN));
        building.setBath(resultSet.getBoolean(BUILDING_BATH));
        building.setDescription(resultSet.getString(BUILDING_DESCRIPTION));
        building.setCountryLocation(resultSet.getString(BUILDING_COUNTRY_LOCATION));
        building.setRegionLocation(resultSet.getString(BUILDING_REGION_LOCATION));
        building.setTownLocation(resultSet.getString(BUILDING_TOWN_LOCATION));
        building.setStreetLocation(resultSet.getString(BUILDING_STREET_LOCATION));
        building.setBuildingLocation(resultSet.getString(BUILDING_BUILDING_LOCATION));
        building.setRoomLocation(resultSet.getString(BUILDING_ROOM_LOCATION));
        return building;
    }

    @Override
    public List<Building> findAll() {
        final String findAllQuery = "select * from m_buildings order by id desc";
        return jdbcTemplate.query(findAllQuery, this::buildingRowMapper);
    }

    @Override
    public List<Building> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_buildings where type = :type order by id desc";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("type", searchParam);
        return namedParameterJdbcTemplate.query(findAllQueryForPrepared,parameterSource,this::buildingRowMapper);
    }

    @Override
    public Optional<Building> findById(Long buildingId) {
        return Optional.ofNullable(findOne(buildingId));
    }

    @Override
    public Building findOne(Long buildingId) {
        final String findById = "select * from m_buildings where id = :buildingId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("buildingId", buildingId);
        return namedParameterJdbcTemplate.queryForObject(findById,parameterSource,this::buildingRowMapper);
    }

    @Override
    public Building save(Building building) {
        final String insertQuery = "insert into m_buildings (type, land_area, rooms_count, total_rooms_area, " +
                "living_area, kitchen_area, building_floors,floor, building_year, repairs, garage, barn, bath, " +
                "description, country_location, region_location, town_location,street_location, building_location, " +
                "room_location)" +
                "values (:type, :land_area, :rooms_count, :total_rooms_area, :living_area, :kitchen_area, " +
                ":building_floors, :floor, :building_year, :repairs, :garage, :barn, :bath, :description, " +
                ":country_location, :region_location, :town_location, :street_location, :building_location, " +
                ":room_location)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("type",building.getType())
                .addValue("land_area",building.getLandArea())
                .addValue("rooms_count",building.getRoomsCount())
                .addValue("total_rooms_area",building.getTotalRoomsArea())
                .addValue("living_area",building.getLivingArea())
                .addValue("kitchen_area",building.getKitchenArea())
                .addValue("building_floors",building.getBuildingFloors())
                .addValue("floor",building.getFloor())
                .addValue("building_year",building.getBuildingYear())
                .addValue("repairs",building.isRepairs())
                .addValue("garage",building.isGarage())
                .addValue("barn",building.isBarn())
                .addValue("bath",building.isBath())
                .addValue("description",building.getDescription())
                .addValue("country_location",building.getCountryLocation())
                .addValue("region_location",building.getRegionLocation())
                .addValue("town_location",building.getTownLocation())
                .addValue("street_location",building.getStreetLocation())
                .addValue("building_location",building.getBuildingLocation())
                .addValue("room_location",building.getRoomLocation());
        namedParameterJdbcTemplate.update(insertQuery,parameterSource,keyHolder,new String[]{"id"});
        long newBuildingId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findOne(newBuildingId);
    }

    @Override
    public Building update(Building building) {
        final String updateQuery = "update m_buildings set type = :type, land_area = :land_area, " +
                "rooms_count = :rooms_count, total_rooms_area = :total_rooms_area, living_area = :living_area, " +
                "kitchen_area = :kitchen_area, building_floors = :building_floors, floor = :floor, " +
                "building_year = :building_year, repairs = :repairs, garage = :garage, barn = :barn, " +
                "bath = :bath, description = :description, country_location = :country_location, " +
                "region_location = :region_location, town_location = :town_location, " +
                "street_location = :street_location, building_location = :building_location, " +
                "room_location = :room_location where id = :buildingId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("type",building.getType())
                .addValue("land_area",building.getLandArea())
                .addValue("rooms_count",building.getRoomsCount())
                .addValue("total_rooms_area",building.getTotalRoomsArea())
                .addValue("living_area",building.getLivingArea())
                .addValue("kitchen_area",building.getKitchenArea())
                .addValue("building_floors",building.getBuildingFloors())
                .addValue("floor",building.getFloor())
                .addValue("building_year",building.getBuildingYear())
                .addValue("repairs",building.isRepairs())
                .addValue("garage",building.isGarage())
                .addValue("barn",building.isBarn())
                .addValue("bath",building.isBath())
                .addValue("description",building.getDescription())
                .addValue("country_location",building.getCountryLocation())
                .addValue("region_location",building.getRegionLocation())
                .addValue("town_location",building.getTownLocation())
                .addValue("street_location",building.getStreetLocation())
                .addValue("building_location",building.getBuildingLocation())
                .addValue("room_location",building.getRoomLocation())
                .addValue("buildingId",building.getId());
        namedParameterJdbcTemplate.update(updateQuery, parameterSource);
        return findOne(building.getId());
    }

    @Override
    public int delete(Building building) {
        final String deleteQuery ="delete from m_buildings where id = :buildingId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("buildingId", building.getId());
        return namedParameterJdbcTemplate.update(deleteQuery,parameterSource);
    }

    @Override
    public List<Building> batchUpdate(List<Building> buildings) {
        final String updateQuery = "update m_buildings set type = :type, land_area = :land_area, " +
                "rooms_count = :rooms_count, total_rooms_area = :total_rooms_area, living_area = :living_area, " +
                "kitchen_area = :kitchen_area, building_floors = :building_floors,floor = :floor, " +
                "building_year = :building_year, repairs = :repairs, garage = :garage, barn = :barn, " +
                "bath = :bath, description = :description, country_location = :country_location, " +
                "region_location = :region_location, town_location = :town_location, " +
                "street_location = :street_location, building_location = :building_location, " +
                "room_location = :room_location " +
                "where id = :buildingId";
        List<SqlParameterSource> batch = new ArrayList<>();
        for (Building building : buildings) {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("type",building.getType())
                    .addValue("land_area",building.getLandArea())
                    .addValue("rooms_count",building.getRoomsCount())
                    .addValue("total_rooms_area",building.getTotalRoomsArea())
                    .addValue("living_area",building.getLivingArea())
                    .addValue("kitchen_area",building.getKitchenArea())
                    .addValue("building_floors",building.getBuildingFloors())
                    .addValue("floor",building.getFloor())
                    .addValue("building_year",building.getBuildingYear())
                    .addValue("repairs",building.isRepairs())
                    .addValue("garage",building.isGarage())
                    .addValue("barn",building.isBarn())
                    .addValue("bath",building.isBath())
                    .addValue("description",building.getDescription())
                    .addValue("country_location",building.getCountryLocation())
                    .addValue("region_location",building.getRegionLocation())
                    .addValue("town_location",building.getTownLocation())
                    .addValue("street_location",building.getStreetLocation())
                    .addValue("building_location",building.getBuildingLocation())
                    .addValue("room_location",building.getRoomLocation())
                    .addValue("buildingId",building.getId());
            batch.add(parameterSource);
        }
        namedParameterJdbcTemplate.batchUpdate(updateQuery,batch.toArray(new SqlParameterSource[batch.size()]));
        return buildings;
    }
}
