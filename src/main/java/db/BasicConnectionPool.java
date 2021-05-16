package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool {
    private static BasicConnectionPool instance = null;
    private static List<Connection> connectionPool;
    private static final int INITIAL_POOL_SIZE = 20;

    private BasicConnectionPool(List<Connection> newPool) {
        connectionPool = newPool;
    }

    public Connection getConnection() {
        return connectionPool
                .remove(connectionPool.size() - 1);
    }

    public static BasicConnectionPool getInstance() {
        if (instance == null) {
            List<Connection> pool = new ArrayList<Connection>(INITIAL_POOL_SIZE);
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                try {
                    Connection connection = createConnection(db.DataSource.getUrl(), db.DataSource.getUsername(), db.DataSource.getPassword());
                    pool.add(connection);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
            instance = new BasicConnectionPool(pool);
            return instance;
        }
        return instance;
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
