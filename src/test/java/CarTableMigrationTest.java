import io.DataExporter;
import migration.MigrationExecutor;
import migration.Query;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTableMigrationTest {
    private static final String carExpected = "car_expected.csv";
    private static final String carResult = "car_result.csv";
    private static final String logFile = "car_log.txt";

    @Test
    public void testNonMigratedUserDataBaseReturnAllRowsEquals() {
        try {
            DataExporter.exportDataFromDb(carResult, "car", DataExporter.getTableColumnsNames("car"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        CsvComparator csvComparator = new CsvComparator(carExpected, carResult, logFile);
        boolean equality = csvComparator.compareCsv();
        assertTrue(equality);
    }

    @Test
    public void migrateUserTableToNewColumn() {
        try {
            MigrationExecutor.executeMigration(Query.NEW_CAR_COLUMN_UP_MIGRATION);
            DataExporter.exportDataFromDb(carResult, "car", DataExporter.getTableColumnsNames("car"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        CsvComparator csvComparator = new CsvComparator(carExpected, carResult, logFile);
        boolean equality = csvComparator.compareCsv();
        MigrationExecutor.executeMigration(Query.CAR_TABLE_DOWN_MIGRATION);
        assertFalse(equality);
    }
}
