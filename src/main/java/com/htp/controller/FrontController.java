package com.htp.controller;

import com.htp.dao.UserDao;
import com.htp.dao.UserDaoImpl;
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
}