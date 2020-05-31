package com.htp.dao;

import com.htp.domain.Building;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;
import static com.htp.util.DatabaseConfiguration.DATABASE_PASSWORD;

public class BuildingDaoImpl implements BuildingDao {

    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();

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

    String driverName = config.getProperty(DATABASE_DRIVER_NAME);
    String url = config.getProperty(DATABASE_URL);
    String login = config.getProperty(DATABASE_LOGIN);
    String databasePassword = config.getProperty(DATABASE_PASSWORD);

    @Override
    public List<Building> findAll() {
        final String findAllQuery = "select * from m_buildings order by id desc";
        List<Building> resultList = new ArrayList<>();
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
    public List<Building> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_buildings where id > ? order by id desc";
        List<Building> resultList = new ArrayList<>();
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
    public Optional<Building> findById(Long buildingId) {
        return Optional.ofNullable(findOne(buildingId));
    }

    @Override
    public Building findOne(Long buildingId) {
        final String findById = "select * from m_buildings where id = ?";
        Building building = null;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(findById);
        ) {
            preparedStatement.setLong(1, buildingId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                building = parseResultSet(resultSet);
            } else {
                throw new ResourceNotFoundException("Building with id " + buildingId + " not found");
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
        return building;
    }

    @Override
    public Building save(Building building) {
        final String insertQuery = "insert into m_buildings (type, land_area, rooms_count, total_rooms_area, " +
                "living_area, kitchen_area, building_floors,floor, building_year, repairs, garage, barn, bath, " +
                "description, country_location, region_location, town_location,street_location, building_location, " +
                "room_location)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String lastInsertId = "SELECT currval('m_building_id_seq') as last_insert_id";
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
            preparedStatement.setString(1, building.getType());
            preparedStatement.setInt(2, building.getLandArea());
            preparedStatement.setInt(3, building.getRoomsCount());
            preparedStatement.setInt(4, building.getTotalRoomsArea());
            preparedStatement.setInt(5, building.getLivingArea());
            preparedStatement.setInt(6, building.getKitchenArea());
            preparedStatement.setInt(7, building.getBuildingFloors());
            preparedStatement.setInt(8, building.getFloor());
            preparedStatement.setInt(9, building.getBuildingYear());
            preparedStatement.setBoolean(10, building.isRepairs());
            preparedStatement.setBoolean(11, building.isGarage());
            preparedStatement.setBoolean(12, building.isBarn());
            preparedStatement.setBoolean(13, building.isBath());
            preparedStatement.setString(14, building.getDescription());
            preparedStatement.setString(15, building.getCountryLocation());
            preparedStatement.setString(16, building.getRegionLocation());
            preparedStatement.setString(17, building.getTownLocation());
            preparedStatement.setString(18, building.getStreetLocation());
            preparedStatement.setString(19, building.getBuildingLocation());
            preparedStatement.setString(20, building.getRoomLocation());
            preparedStatement.executeUpdate();
            set = preparedStatementLastInsertId.executeQuery();
            set.next();
            long insertedBuildingId = set.getInt("last_insert_id");
            return findOne(insertedBuildingId);
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
    public Building update(Building building) {
        final String updateQuery = "update m_buildings set type = ?, land_area = ?, rooms_count = ?, " +
                "total_rooms_area = ?, living_area = ?, kitchen_area = ?, building_floors = ?,floor = ?, " +
                "building_year = ?, repairs = ?, garage = ?, barn = ?, bath = ?, description = ?, " +
                "country_location = ?, region_location = ?, town_location = ?,street_location = ?, " +
                "building_location = ?, room_location = ?" +
                "where id = ?";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, building.getType());
            preparedStatement.setInt(2, building.getLandArea());
            preparedStatement.setInt(3, building.getRoomsCount());
            preparedStatement.setInt(4, building.getTotalRoomsArea());
            preparedStatement.setInt(5, building.getLivingArea());
            preparedStatement.setInt(6, building.getKitchenArea());
            preparedStatement.setInt(7, building.getBuildingFloors());
            preparedStatement.setInt(8, building.getFloor());
            preparedStatement.setInt(9, building.getBuildingYear());
            preparedStatement.setBoolean(10, building.isRepairs());
            preparedStatement.setBoolean(11, building.isGarage());
            preparedStatement.setBoolean(12, building.isBarn());
            preparedStatement.setBoolean(13, building.isBath());
            preparedStatement.setString(14, building.getDescription());
            preparedStatement.setString(15, building.getCountryLocation());
            preparedStatement.setString(16, building.getRegionLocation());
            preparedStatement.setString(17, building.getTownLocation());
            preparedStatement.setString(18, building.getStreetLocation());
            preparedStatement.setString(19, building.getBuildingLocation());
            preparedStatement.setString(20, building.getRoomLocation());
            preparedStatement.setLong(21, building.getId());
            preparedStatement.executeUpdate();
            return findOne(building.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public int delete(Building building) {
        final String deleteQuery ="delete from m_buildings where id = ?";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)
        ) {
            preparedStatement.setLong(1, building.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public List<Building> batch(Building building) {
        final String insertQuery = "insert into m_buildings (type, land_area, rooms_count, total_rooms_area, " +
                "living_area, kitchen_area, building_floors,floor, building_year, repairs, garage, barn, bath, " +
                "description, country_location, region_location, town_location,street_location, building_location, " +
                "room_location)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String listBuildings = "SELECT * from m_buildings order by id desc limit 2";
        List<Building> resultList = new ArrayList<>();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             PreparedStatement preparedStatementListBuildings = connection.prepareStatement(listBuildings)
        ) {
            connection.setAutoCommit(false);
            for (int i = 0; i < 2 ; i++) {
                preparedStatement.setString(1, building.getType());
                preparedStatement.setInt(2, building.getLandArea());
                preparedStatement.setInt(3, building.getRoomsCount());
                preparedStatement.setInt(4, building.getTotalRoomsArea());
                preparedStatement.setInt(5, building.getLivingArea());
                preparedStatement.setInt(6, building.getKitchenArea());
                preparedStatement.setInt(7, building.getBuildingFloors());
                preparedStatement.setInt(8, building.getFloor());
                preparedStatement.setInt(9, building.getBuildingYear());
                preparedStatement.setBoolean(10, building.isRepairs());
                preparedStatement.setBoolean(11, building.isGarage());
                preparedStatement.setBoolean(12, building.isBarn());
                preparedStatement.setBoolean(13, building.isBath());
                preparedStatement.setString(14, building.getDescription());
                preparedStatement.setString(15, building.getCountryLocation());
                preparedStatement.setString(16, building.getRegionLocation());
                preparedStatement.setString(17, building.getTownLocation());
                preparedStatement.setString(18, building.getStreetLocation());
                preparedStatement.setString(19, building.getBuildingLocation());
                preparedStatement.setString(20, building.getRoomLocation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            ResultSet set = preparedStatementListBuildings.executeQuery();
            while (set.next()) {
                resultList.add(parseResultSet(set));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
        return resultList;
    }

    private Building parseResultSet(ResultSet resultSet) throws SQLException {
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
}
