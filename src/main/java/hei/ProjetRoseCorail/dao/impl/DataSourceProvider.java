package hei.ProjetRoseCorail.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("rose_corail");
            dataSource.setUser("root");
            dataSource.setPassword("");
        }
        return dataSource;
    }
}
