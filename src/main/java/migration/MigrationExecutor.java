package migration;

import db.BasicConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MigrationExecutor {
    public static void executeMigration(Query migration) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(migration.query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
