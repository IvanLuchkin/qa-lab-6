import io.DataExporter;
import migration.MigrationExecutor;
import migration.Query;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTableMigrationTest {
    private static final String userExpected = "user_expected.csv";
    private static final String userResult = "user_result.csv";
    private static final String logFile = "user_log.txt";

    @Test
    public void testNonMigratedUserDataBaseReturnAllRowsEquals() {
        try {
            DataExporter.exportDataFromDb(userResult, "user", DataExporter.getTableColumnsNames("user"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        CsvComparator csvComparator = new CsvComparator(userExpected, userResult, logFile);
        boolean equality = csvComparator.compareCsv();
        assertTrue(equality);
    }

    @Test
    public void migrateUserTableToNewColumn() {
        try {
            MigrationExecutor.executeMigration(Query.NEW_USER_UP_MIGRATION);
            DataExporter.exportDataFromDb(userResult, "user", DataExporter.getTableColumnsNames("user"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        CsvComparator csvComparator = new CsvComparator(userExpected, userResult, logFile);
        boolean equality = csvComparator.compareCsv();
        MigrationExecutor.executeMigration(Query.USER_TABLE_DOWN_MIGRATION);
        assertFalse(equality);
    }

}
