package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.helper.Helper;
import com.mitrais.rms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/users/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserServlet extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getTemplatePath(req.getServletPath() + req.getPathInfo());

        if ("/list".equalsIgnoreCase(req.getPathInfo())) {
            UserDao userDao = UserDaoImpl.getInstance();
            List<User> users = userDao.findAll();
            users=users.stream()
                    .peek(user -> user.setPicture(Helper.getRouteLink(req,user.getPicture())))
                    .collect(Collectors.toList());
            req.setAttribute("users", users);

        }

        if(req.getPathInfo()!=null && req.getPathInfo().contains("delete")){
            doDelete(req,resp);
            return;
        }
        dispatcher(req, resp, path);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = generateUserFromRequest(req);
        user.setId(Long.valueOf(req.getPathInfo().replace("/","")));

        if(!req.getParts().isEmpty()){
            req.getParts().forEach(part -> {
                if(part.getContentType()!=null) {
                    if (part.getContentType().contains("image"))
                        user.setPicture(Helper.store("user", part, req));
                }
            });
        }

        UserDao userDao = UserDaoImpl.getInstance();
        userDao.update(user);

        resp.sendRedirect(Helper.getRouteLink(req, "users/list"));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getPathInfo().replace("/delete/","");
        User user = generateUserFromRequest(req);
        user.setId(Long.valueOf(id));

        UserDao userDao = UserDaoImpl.getInstance();
        userDao.delete(user);

        resp.sendRedirect(Helper.getRouteLink(req, "users/list"));
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getPathInfo().replace("list","").equalsIgnoreCase("/")){
            doPut(req,resp);
            return;
        }

        User user = generateUserFromRequest(req);

        if(!req.getParts().isEmpty()){
            req.getParts().forEach(part -> {
                if(part.getContentType()!=null) {
                    if (part.getContentType().contains("image"))
                        user.setPicture(Helper.store("user", part, req));
                }
            });
        }

        UserDao userDao = UserDaoImpl.getInstance();
        userDao.save(user);

        resp.sendRedirect(Helper.getRouteLink(req, "users/list"));
    }

    private User generateUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setUserName(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setName(req.getParameter("name"));
        user.setAddress(req.getParameter("address"));
        user.setEmail(req.getParameter("email"));
        user.setPicture(req.getParameter("picture"));
        return user;
    }
}
