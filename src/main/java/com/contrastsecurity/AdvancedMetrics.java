package com.contrastsecurity;

import java.sql.SQLException;

public class AdvancedMetrics {

	private static DBUtil dbCall;

	public AdvancedMetrics() {
		dbCall = new DBUtil();
	}

	private static void getHostSummary() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.host_summary");
	}

	private static void getHostSummaryByFileIo() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.host_summary_by_file_io");
	}

	private static void getHostSummaryByFileIoType() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.host_summary_by_file_io_type");
	}

	private static void getHostSummaryByStages() throws SQLException {
		dbCall.resultSetToJson("SELECT *  FROM sys.host_summary_by_stages;");
	}

	private static void getHostSummaryByStatementLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.host_summary_by_statement_latency;");
	}

	private static void getHostSummaryByStatementType() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.host_summary_by_statement_type");
	}

	private static void getInnodbBufferStatsBySchema() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.innodb_buffer_stats_by_schema");
	}

	private static void getInnodbBufferStatsByTable() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.innodb_buffer_stats_by_table");
	}

	private static void getInnodbLockWaits() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.innodb_lock_waits");
	}

	private static void getIoByThreadLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.io_by_thread_by_latency");
	}

	private static void getIoGlobalByFileByBytes() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.io_global_by_file_by_bytes LIMIT 5");
	}

	private static void getIoGlobalByFileByLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.io_global_by_file_by_latency limit 5;");
	}

	private static void getIoGlobalByWaitByBytes() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.io_global_by_wait_by_bytes");
	}

	private static void getIoGlobalByWaitByLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.io_global_by_wait_by_latency");
	}

	private static void getLatestFileIo() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.latest_file_io limit 5");
	}

	private static void getMetrics() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.metrics WHERE type != 'GLOBAL STATUS'");
	}

	private static void getPsCheckLostInstrumentation() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.ps_check_lost_instrumentation");
	}

	private static void getSchemaAutoIncrementColumns() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.schema_auto_increment_columns limit 20");
	}

	private static void getSchemaIndexStatistics() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.schema_index_statistics limit 20");
	}

	private static void getSchemaObjectOverview() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.schema_object_overview");
	}

	private static void schemaTableStatistics() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.schema_table_statistics");
	}

	private static void schemaTableStatisticsWithBuffer() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.schema_table_statistics_with_buffer limit 10");
	}

	private static void schemaTablesWithFullTableScans() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.schema_tables_with_full_table_scans limit 10");
	}

	private static void getSchemaUnusedIndexes() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.schema_unused_indexes limit 10");
	}

	private static void getStatementsWithErrorsOrWarnings() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.statements_with_errors_or_warnings LIMIT 10");
	}

	private static void getStatementsWithFullTableScans() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.statements_with_full_table_scans limit 10");
	}

	private static void getStatementsWithRunTimesIn95thPercentile() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.statements_with_runtimes_in_95th_percentile");
	}

	private static void getStatementsWithSorting() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.statements_with_sorting limit 10");
	}

	private static void getStatementsWithTempTables() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.statements_with_temp_tables limit 1");
	}

	private static void getUserSummary() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.user_summary");
	}

	private static void getUserSummaryByFileIo() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.user_summary_by_file_io");
	}

	private static void getUserSummaryByFileIoType() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.user_summary_by_file_io_type");
	}

	private static void getUserSummaryByStages() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.user_summary_by_stages");
	}

	private static void getUserSummaryByStatementLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.user_summary_by_statement_latency");
	}

	private static void getUserSummaryByStatementType() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.user_summary_by_statement_type");
	}

	private static void getWaitClassesGlobalByAvgLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.wait_classes_global_by_avg_latency WHERE event_class != 'idle'");
	}

	private static void getWaitClassesGlobalByLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.wait_classes_global_by_latency");
	}

	private static void getWaitsByUserByLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.waits_by_user_by_latency;");
	}

	private static void getWaitsByHostByLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.waits_by_host_by_latency");
	}

	private static void getWaitsGlobalByLatency() throws SQLException {
		dbCall.resultSetToJson("SELECT * FROM sys.waits_global_by_latency");
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
		
		dbCall.close();
	}

}
