package com.mitrais.rms.dao.impl;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class UserDaoImpl implements UserDao {
    @Override
    public Optional<User> find(Long id) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            stmt.setLong(1, id);
            Optional<User> user = getUser(stmt);
            if (user != null) return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                User user = new User(rs.getLong("id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("picture")
                );
                result.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean save(User user) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?, ?, ?,?)");
            setParameter(user, stmt);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private void setParameter(User user, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, user.getUserName()==null?"":user.getUserName());
        stmt.setString(2, user.getPassword()==null?"":user.getPassword());
        stmt.setString(3, user.getName()==null?"":user.getName());
        stmt.setString(4, user.getAddress()==null?"":user.getAddress());
        stmt.setString(5, user.getEmail()==null?"":user.getEmail());
        stmt.setString(6, user.getPicture()==null?"":user.getPicture());
    }

    private void setParameter(User user, PreparedStatement stmt, User old) throws SQLException{
        stmt.setString(1, user.getUserName()==null?old.getUserName():user.getUserName());
        stmt.setString(2, user.getPassword()==null?old.getPassword():user.getPassword());
        stmt.setString(3, user.getName()==null?old.getName():user.getName());
        stmt.setString(4, user.getAddress()==null?old.getAddress():user.getAddress());
        stmt.setString(5, user.getEmail()==null?old.getEmail():user.getEmail());
        stmt.setString(6, user.getPicture()==null?old.getPicture():user.getPicture());
    }

    @Override
    public boolean update(User user) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            User old=this.find(user.getId()).orElse(null);
            PreparedStatement stmt =
                    connection.prepareStatement("UPDATE user SET " +
                            "user_name=?, " +
                            "password=?, " +
                            "name=?, " +
                            "address=?, " +
                            "email=?, " +
                            "picture=? " +
                            "WHERE id=?");
            setParameter(user, stmt, old);
            stmt.setLong(7, user.getId());
            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM user WHERE id=?");
            stmt.setLong(1, user.getId());
            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE user_name=?");
            stmt.setString(1, userName);
            Optional<User> user = getUser(stmt);
            if (user != null) return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    private Optional<User> getUser(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            User user = new User(rs.getLong("id"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("picture")
            );
            return Optional.of(user);
        }
        return null;
    }

    private static class SingletonHelper {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDao getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
