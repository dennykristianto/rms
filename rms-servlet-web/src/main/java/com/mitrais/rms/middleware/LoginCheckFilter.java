package com.mitrais.rms.middleware;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        String loginUri=req.getContextPath()+"/account/login";

        boolean isLoggedIn=req.getSession().getAttribute("user")!=null && !req.getSession().getAttribute("user").equals("");
        boolean loginRequest = req.getRequestURI().equals(loginUri);

        if(isLoggedIn ||loginRequest){ // sudah login
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginUri);
        }
    }

    @Override
    public void destroy() {

    }
}
