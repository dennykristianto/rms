package com.mitrais.rms.services;


import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

public class UserService {
    private static UserService instance;

    public User loginUser(String username, String password){
        UserDao userDao=new UserDaoImpl();
        User user = userDao.findByUserName(username).orElse(null);
        return user != null && user.getPassword().equals(password)?user:null;
    }

    private UserService(){}

    public static UserService getInstance(){
        if(instance==null) instance=new UserService();
        return instance;
    }
}
