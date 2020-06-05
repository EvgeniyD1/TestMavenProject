package com.htp.controller;

import com.htp.dao.*;
import com.htp.domain.Building;
import com.htp.domain.Role;
import com.htp.domain.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FrontController extends HttpServlet {

    public static final String FIND_ONE = "findOne";
    public static final String FIND_BY_ID = "findById";
    public static final String FIND_ALL = "findAll";
    public static final String SAVE = "save";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String BATCH = "batch";

    private UserDao userDao = new UserDaoImpl();
//    private BuildingDao buildingDao = new BuildingDaoImpl();
//    private RoleDAO roleDao = new RoleDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchQuery = StringUtils.isNotBlank(req.getParameter("userId")) ? req.getParameter("userId") : "0";
        String typeOfSearch = StringUtils.isNotBlank(req.getParameter("type")) ? req.getParameter("type") : "0";
        String userName = StringUtils.isNotBlank(req.getParameter("userName")) ? req.getParameter("userName") : "0";

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
            if (dispatcher != null) {

                String result = "";

                switch (typeOfSearch) {
                    case FIND_ONE:
                        result = userDao.findOne(Long.parseLong(searchQuery)).getLogin();
                        break;
                    case FIND_BY_ID:
                        Optional<User> optionalUser = userDao.findById(Long.parseLong(searchQuery));
                        if (optionalUser.isPresent()) {
                            result = optionalUser.get().getLogin();
                        }
                        break;
                    case SAVE:
                        User user = new User();
                        user.setUsername(userName);
                        user.setSurname(userName);
                        user.setPatronymic(userName);
                        user.setPhoneNumber("88006663636");
                        user.setLogin(UUID.randomUUID().toString());
                        user.setPassword(userName);
                        user.setCreated(new Timestamp(new Date().getTime()));
                        user.setChanged(new Timestamp(new Date().getTime()));
                        user.setBirthDate(new java.sql.Date(new Date().getTime()));
                        user.setMail("123@mail.ru");
                        user.setCountryLocation("Belarus");
                        result = userDao.save(user).getLogin();
                        break;
                    case UPDATE:
                        User userForUpdate = userDao.findOne(Long.parseLong(searchQuery));
                        userForUpdate.setUsername(userName);
                        userForUpdate.setChanged(new Timestamp(new Date().getTime()));
                        result = userDao.update(userForUpdate).getLogin();
                        break;
                    case DELETE:
                        User userForDelete = userDao.findOne(Long.parseLong(searchQuery));
                        result = Integer.toString(userDao.delete(userForDelete));
                        break;
                    case BATCH:
                        User userBatch = new User();
                        userBatch.setUsername(userName);
                        userBatch.setSurname(userName);
                        userBatch.setPatronymic(userName);
                        userBatch.setPhoneNumber("88006663636");
                        userBatch.setLogin(UUID.randomUUID().toString());
                        userBatch.setPassword(userName);
                        userBatch.setCreated(new Timestamp(new Date().getTime()));
                        userBatch.setChanged(new Timestamp(new Date().getTime()));
                        userBatch.setBirthDate(new java.sql.Date(new Date().getTime()));
                        userBatch.setMail("123@mail.ru");
                        userBatch.setCountryLocation("Belarus");
                        result = userDao.batch(userBatch).stream().map(User::getLogin).collect(Collectors.joining(","));
                        break;
                    case FIND_ALL:
                    default:
                        result = userDao.findAll().stream().map(User::getLogin).collect(Collectors.joining(","));
                        break;
                }

                req.setAttribute("userNames", result);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
            req.setAttribute("errors", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }

//    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String searchQuery = StringUtils.isNotBlank(req.getParameter("Id")) ? req.getParameter("Id") : "0";
//        String typeOfSearch = StringUtils.isNotBlank(req.getParameter("type")) ? req.getParameter("type") : "0";
//        String buildingName = StringUtils.isNotBlank(req.getParameter("buildingName")) ? req.getParameter("buildingName") : "0";
//
//        try {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
//            if (dispatcher != null) {
//
//                String result = "";
//
//                switch (typeOfSearch) {
//                    case FIND_ONE:
//                        result = buildingDao.findOne(Long.parseLong(searchQuery)).getType();
//                        break;
//                    case FIND_BY_ID:
//                        Optional<Building> optionalBuilding = buildingDao.findById(Long.parseLong(searchQuery));
//                        if (optionalBuilding.isPresent()) {
//                            result = optionalBuilding.get().getType();
//                        }
//                        break;
//                    case SAVE:
//                        Building building = new Building();
//                        building.setType(buildingName);
//                        building.setLandArea(123);
//                        building.setRoomsCount(123);
//                        building.setTotalRoomsArea(123);
//                        building.setLivingArea(123);
//                        building.setKitchenArea(123);
//                        building.setBuildingFloors(123);
//                        building.setFloor(123);
//                        building.setBuildingYear(123);
//                        building.setDescription("aaa");
//                        building.setCountryLocation(buildingName);
//                        building.setRegionLocation(buildingName);
//                        building.setTownLocation(buildingName);
//                        building.setStreetLocation(buildingName);
//                        building.setBuildingLocation(buildingName);
//                        building.setRoomLocation(buildingName);
//                        result = buildingDao.save(building).getType();
//                        break;
//                    case UPDATE:
//                        Building buildingForUpdate = buildingDao.findOne(Long.parseLong(searchQuery));
//                        buildingForUpdate.setType(buildingName);
//                        result = buildingDao.update(buildingForUpdate).getType();
//                        break;
//                    case DELETE:
//                        Building buildingForDelete = buildingDao.findOne(Long.parseLong(searchQuery));
//                        result = Integer.toString(buildingDao.delete(buildingForDelete));
//                        break;
//                    case BATCH:
//                        Building buildingBatch = new Building();
//                        buildingBatch.setType(buildingName);
//                        buildingBatch.setLandArea(123);
//                        buildingBatch.setRoomsCount(123);
//                        buildingBatch.setTotalRoomsArea(123);
//                        buildingBatch.setLivingArea(123);
//                        buildingBatch.setKitchenArea(123);
//                        buildingBatch.setBuildingFloors(123);
//                        buildingBatch.setFloor(123);
//                        buildingBatch.setBuildingYear(123);
//                        buildingBatch.setDescription("aaa");
//                        buildingBatch.setCountryLocation(buildingName);
//                        buildingBatch.setRegionLocation(buildingName);
//                        buildingBatch.setTownLocation(buildingName);
//                        buildingBatch.setStreetLocation(buildingName);
//                        buildingBatch.setBuildingLocation(buildingName);
//                        buildingBatch.setRoomLocation(buildingName);
//                        result = buildingDao.batch(buildingBatch).stream().map(Building::getType).collect(Collectors.joining(","));
//                        break;
//                    case FIND_ALL:
//                    default:
//                        result = buildingDao.findAll().stream().map(Building::getType).collect(Collectors.joining(","));
//                        break;
//                }
//
//                req.setAttribute("userNames", result);
//                dispatcher.forward(req, resp);
//            }
//        } catch (Exception e) {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
//            req.setAttribute("errors", e.getMessage());
//            dispatcher.forward(req, resp);
//        }
//    }

//    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String searchQuery = StringUtils.isNotBlank(req.getParameter("roleId")) ? req.getParameter("roleId") : "0";
//        String typeOfSearch = StringUtils.isNotBlank(req.getParameter("type")) ? req.getParameter("type") : "0";
//        String roleName = StringUtils.isNotBlank(req.getParameter("roleName")) ? req.getParameter("roleName") : "0";
//
//        try {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
//            if (dispatcher != null) {
//
//                String result = "";
//
//                switch (typeOfSearch) {
//                    case FIND_ONE:
//                        result = roleDao.findOne(Long.parseLong(searchQuery)).getRoleName();
//                        break;
//                    case FIND_BY_ID:
//                        Optional<Role> optionalRole = roleDao.findById(Long.parseLong(searchQuery));
//                        if (optionalRole.isPresent()) {
//                            result = optionalRole.get().getRoleName();
//                        }
//                        break;
//                    case SAVE:
//                        Role role = new Role();
//                        role.setRoleName(roleName);
//                        role.setUserId(Long.parseLong(searchQuery));
//                        result = roleDao.save(role).getRoleName();
//                        break;
//                    case UPDATE:
//                        Role roleForUpdate = roleDao.findOne(Long.parseLong(searchQuery));
//                        roleForUpdate.setRoleName(roleName);
//                        result = roleDao.update(roleForUpdate).getRoleName();
//                        break;
//                    case DELETE:
//                        Role roleForDelete = roleDao.findOne(Long.parseLong(searchQuery));
//                        result = Integer.toString(roleDao.delete(roleForDelete));
//                        break;
//                    case BATCH:
//                        Role roleBatch = new Role();
//                        roleBatch.setRoleName(roleName);
//                        roleBatch.setUserId(Long.parseLong(searchQuery));
//                        result = roleDao.batch(roleBatch).stream().map(Role::getRoleName).collect(Collectors.joining(","));
//                        break;
//                    case FIND_ALL:
//                    default:
//                        result = roleDao.findAll().stream().map(Role::getRoleName).collect(Collectors.joining(","));
//                        break;
//                }
//
//                req.setAttribute("userNames", result);
//                dispatcher.forward(req, resp);
//            }
//        } catch (Exception e) {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
//            req.setAttribute("errors", e.getMessage());
//            dispatcher.forward(req, resp);
//        }
//    }
}