package io;

import db.BasicConnectionPool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataExporter {

    public static void exportDataFromDb(String fileOut,String tableName,List<String> columns) throws IOException, SQLException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileOut));
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from \"" + tableName + "\" order by id";
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            StringBuilder totalLine = new StringBuilder();
            for (int i = 0; i < columns.size(); i++) {
                if (i == columns.size() - 1) {
                    totalLine.append(result.getObject(columns.get(i)).toString());
                } else {
                    totalLine.append(result.getObject(columns.get(i)).toString()).append(",");
                }
            }
            totalLine.append("\n");
            fileWriter.write(totalLine.toString());
        }
        fileWriter.close();
        statement.close();
    }

    public static List<String> getTableColumnsNames(String table){
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        String query = "select * from \"" + table + "\"";
        List<String> columns = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            for (int it = 1; it <= count ; it++) {
                columns.add(metaData.getColumnName(it));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columns;
    }
}
