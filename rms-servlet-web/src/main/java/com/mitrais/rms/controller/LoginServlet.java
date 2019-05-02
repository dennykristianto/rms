package com.mitrais.rms.controller;

import com.mitrais.rms.model.User;
import com.mitrais.rms.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account/*")
public class LoginServlet extends AbstractController
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        switch (req.getPathInfo().replace("/","")){
            case "logout":
                req.getSession().removeAttribute("user");
                resp.sendRedirect(req.getContextPath());
                break;
            case "login":
            default:
                if(req.getSession().getAttribute("user")!=null && !req.getSession().getAttribute("user").equals("")) {
                    resp.sendRedirect(req.getContextPath());
                    return;
                }
                String path = getTemplatePath(req.getPathInfo());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                requestDispatcher.forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        User user=(UserService.getInstance().loginUser(req.getParameter("username"),req.getParameter("password")));
        if(user!=null){
            req.getSession().setAttribute("user",user.getId());
            resp.sendRedirect(req.getContextPath()+"/account/login");
        }else{
            req.setAttribute("error","Login failed");
            String path = getTemplatePath(req.getPathInfo());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        }
    }

}
