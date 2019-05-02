package com.mitrais.rms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet
{
    public static final String VIEW_PREFIX = "/WEB-INF/jsp";
    public static final String VIEW_SUFFIX = ".jsp";

    protected String getTemplatePath(String path)
    {
        if (path.equalsIgnoreCase("/"))
        {
            return VIEW_PREFIX + path + "index" + VIEW_SUFFIX;
        }
        else
        {
            return VIEW_PREFIX + path + VIEW_SUFFIX;
        }
    }

    protected void dispatcher(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        req.setAttribute("router", path);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(getTemplatePath("/"));
        requestDispatcher.forward(req, resp);
    }
}
