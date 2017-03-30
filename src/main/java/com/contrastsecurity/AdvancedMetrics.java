package com.contrastsecurity;

import java.sql.SQLException;

public class AdvancedMetrics {

	private static DBUtil dbCall;

	public AdvancedMetrics() {
		dbCall = new DBUtil();
	}

	public static void getHostSummary() throws SQLException {
		dbCall.resultSetToJson("select * from sys.host_summary;");
	}

	public static void getHostSummaryByFileIo() throws SQLException {
		dbCall.resultSetToJson("select * from sys.host_summary_by_file_io");
	}

	public static void getHostSummaryByFileIoType() throws SQLException {
		dbCall.resultSetToJson("select * from sys.host_summary_by_file_io_type");
	}

	public static void getHostSummaryByStages() throws SQLException {
		dbCall.resultSetToJson("select *  from sys.host_summary_by_stages;");
	}

	public static void getHostSummaryByStatementLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.host_summary_by_statement_latency;");
	}

	public static void getHostSummaryByStatementType() throws SQLException {
		dbCall.resultSetToJson("select * from sys.host_summary_by_statement_type");
	}

	public static void getInnodbBufferStatsBySchema() throws SQLException {
		dbCall.resultSetToJson("select * from sys.innodb_buffer_stats_by_schema");
	}

	public static void getInnodbBufferStatsByTable() throws SQLException {
		dbCall.resultSetToJson("select * from sys.innodb_buffer_stats_by_table");
	}

	public static void getInnodbLockWaits() throws SQLException {
		dbCall.resultSetToJson("SELECT * from sys.innodb_lock_waits");
	}

	public static void getIoByThreadLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.io_by_thread_by_latency");
	}

	public static void getIoGlobalByFileByBytes() throws SQLException {
		dbCall.resultSetToJson("SELECT * from sys.io_global_by_file_by_bytes LIMIT 5");
	}

	public static void getIoGlobalByFileByLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.io_global_by_file_by_latency limit 5;");
	}

	public static void getIoGlobalByWaitByBytes() throws SQLException {
		dbCall.resultSetToJson("select * from sys.io_global_by_wait_by_bytes");
	}

	public static void getIoGlobalByWaitByLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * from sys.io_global_by_wait_by_latency");
	}

	public static void getLatestFileIo() throws SQLException {
		dbCall.resultSetToJson("select * from sys.latest_file_io limit 5");
	}

	public static void getMetrics() throws SQLException {
		dbCall.resultSetToJson("SELECT * from sys.metrics WHERE type != 'GLOBAL STATUS'");
	}

	public static void getPsCheckLostInstrumentation() throws SQLException {
		dbCall.resultSetToJson("select * from sys.ps_check_lost_instrumentation");
	}

	public static void getSchemaAutoIncrementColumns() throws SQLException {
		dbCall.resultSetToJson("select * from sys.schema_auto_increment_columns limit 20");
	}

	public static void getSchemaIndexStatistics() throws SQLException {
		dbCall.resultSetToJson("select * from sys.schema_index_statistics limit 20");
	}

	public static void getSchemaObjectOverview() throws SQLException {
		dbCall.resultSetToJson("select * from sys.schema_object_overview");
	}

	public static void schemaTableStatistics() throws SQLException {
		dbCall.resultSetToJson("select * from sys.schema_table_statistics");
	}

	public static void schemaTableStatisticsWithBuffer() throws SQLException {
		dbCall.resultSetToJson("select * from sys.schema_table_statistics_with_buffer limit 10");
	}

	public static void schemaTablesWithFullTableScans() throws SQLException {
		dbCall.resultSetToJson("select * from sys.schema_tables_with_full_table_scans limit 10");
	}

	public static void getSchemaUnusedIndexes() throws SQLException {
		dbCall.resultSetToJson("select * from sys.schema_unused_indexes limit 10");
	}

	public static void getStatementsWithErrorsOrWarnings() throws SQLException {
		dbCall.resultSetToJson("select * from sys.statements_with_errors_or_warnings LIMIT 10");
	}

	public static void getStatementsWithFullTableScans() throws SQLException {
		dbCall.resultSetToJson("select * from sys.statements_with_full_table_scans limit 10");
	}

	public static void getStatementsWithRunTimesIn95thPercentile() throws SQLException {
		dbCall.resultSetToJson("select * from sys.statements_with_runtimes_in_95th_percentile");
	}

	public static void getStatementsWithSorting() throws SQLException {
		dbCall.resultSetToJson("select * from sys.statements_with_sorting limit 10");
	}

	public static void getStatementsWithTempTables() throws SQLException {
		dbCall.resultSetToJson("select * from sys.statements_with_temp_tables limit 1");
	}

	public static void getUserSummary() throws SQLException {
		dbCall.resultSetToJson("select * from sys.user_summary");
	}

	public static void getUserSummaryByFileIo() throws SQLException {
		dbCall.resultSetToJson("select * from sys.user_summary_by_file_io");
	}

	public static void getUserSummaryByFileIoType() throws SQLException {
		dbCall.resultSetToJson("select * from sys.user_summary_by_file_io_type");
	}

	public static void getUserSummaryByStages() throws SQLException {
		dbCall.resultSetToJson("select * from sys.user_summary_by_stages");
	}

	public static void getUserSummaryByStatementLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.user_summary_by_statement_latency");
	}

	public static void getUserSummaryByStatementType() throws SQLException {
		dbCall.resultSetToJson("select * from sys.user_summary_by_statement_type");
	}

	public static void getWaitClassesGlobalByAvgLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.wait_classes_global_by_avg_latency where event_class != 'idle'");
	}

	public static void getWaitClassesGlobalByLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.wait_classes_global_by_latency");
	}

	public static void getWaitsByUserByLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.waits_by_user_by_latency;");
	}

	public static void getWaitsByHostByLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.waits_by_host_by_latency");
	}

	public static void getWaitsGlobalByLatency() throws SQLException {
		dbCall.resultSetToJson("select * from sys.waits_global_by_latency");
	}

	public static void stats() throws Exception {
		getHostSummary();
		getHostSummaryByFileIo();
		getHostSummaryByFileIoType();
		getHostSummaryByStages();
		getHostSummaryByStatementLatency();
		getHostSummaryByStatementType();
		getInnodbBufferStatsBySchema();
		getInnodbBufferStatsByTable();
		getInnodbLockWaits();
		getIoByThreadLatency();
		getIoGlobalByFileByBytes();
		getIoGlobalByFileByLatency();
		getIoGlobalByWaitByBytes();
		getIoGlobalByWaitByLatency();
		getLatestFileIo();
		getMetrics();
		getPsCheckLostInstrumentation();
		getSchemaAutoIncrementColumns();
		getSchemaIndexStatistics();
		getSchemaObjectOverview();
		getSchemaUnusedIndexes();
		getStatementsWithErrorsOrWarnings();
		getStatementsWithFullTableScans();
		getStatementsWithRunTimesIn95thPercentile();
		getStatementsWithSorting();
		getStatementsWithTempTables();
		getUserSummary();
		getUserSummaryByFileIo();
		getUserSummaryByFileIoType();
		getUserSummaryByStages();
		getUserSummaryByStatementLatency();
		getUserSummaryByStatementType();
		getWaitClassesGlobalByAvgLatency();
		getWaitClassesGlobalByLatency();
		getWaitsByUserByLatency();
		getWaitsByHostByLatency();
		getWaitsGlobalByLatency();
	}

}
