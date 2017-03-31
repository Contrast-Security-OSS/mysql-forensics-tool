package com.contrastsecurity.tools.forensics.mysql;

import java.sql.SQLException;

public class Metrics extends DefaultConfiguration {

    private static DBUtil queryExec;

    private static void getTableSizes() throws Exception {
        String parameterizedQuery = "SELECT  table_name AS `Table`, "
                + "round(((data_length + index_length) / 1024 / 1024), 2) `MB`, " + "index_length as 'Objects' "
                + "FROM information_schema.TABLES " + "WHERE table_schema = ? " + "order by index_length DESC";
        queryExec.resultSetToJson(parameterizedQuery, getDbSchema());

    }

    private static void getVariables() throws SQLException {
        queryExec.resultSetToJson("SHOW GLOBAL STATUS");

    }

    private static void getVersion() throws SQLException {
        queryExec.resultSetToJson("SELECT VERSION() as version");
    }

    public static void generate() throws Exception {

        queryExec = new DBUtil();

        getVersion();
        getVariables();
        getTableSizes();

        queryExec.close();
    }

}
