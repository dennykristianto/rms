package com.mitrais.rms.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides MySQL datasource to be used to connect to database.
 * It implements singleton pattern See <a href="http://www.oodesign.com/singleton-pattern.html">Singleton Pattern</a>
 */
public class DataSourceFactory
{
    private final DataSource dataSource;

    DataSourceFactory()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        try(InputStream input=DataSourceFactory.class.getClassLoader().getResourceAsStream("database.properties")){
            Properties properties=new Properties();
            properties.load(input);

            dataSource.setDatabaseName(properties.getProperty("database.name"));
            dataSource.setServerName(properties.getProperty("database.server"));
            dataSource.setPort(Integer.valueOf(properties.getProperty("database.port")));
            dataSource.setUser(properties.getProperty("database.user"));
            dataSource.setPassword(properties.getProperty("database.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dataSource = dataSource;
    }

    /**
     * Get a data source to database
     *
     * @return DataSource object
     */
    public static Connection getConnection() throws SQLException
    {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
