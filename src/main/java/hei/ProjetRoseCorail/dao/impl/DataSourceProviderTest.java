package hei.ProjetRoseCorail.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

/**
 * Connexion à la BDD Test
 */
public class DataSourceProviderTest {
    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("rose_corail_test");
            dataSource.setUser("root");
            dataSource.setPassword("");
        }
        return dataSource;
    }
}
