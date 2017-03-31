MySQL Forensic Tool for TeamServer
===================

Want database metrics from your TeamServer installation? Great!

This project will build a runnable jar that will allow you to log core
metric data from your MySQL database whether it is local or distributed. 

The runnable jar file must be able to locally access the configuration files 
for TeamServer. It will decrypt your database credentials and start logging database metrics to your
terminal. 

## Example Metrics

* MySQL Version
* Global Variables
* Table Volumetrics
* Host summary by File IO and Stages
* InnoDB Buffer Stats by Schema and Tables
* InnoDB Lock Waits
* IO Thread Latency
* Global IO by File 
* Global IO Wait by latency
* Lost Instrumention
* Schema index statistics
* Statements with errors or warnings
* Statements with run times in 95th percentile

## Requirements for building this project
* Java 8 JDK or OpenJDK 8
* Maven 

## Building 
To build the runnable jar execute:

	mvn clean compile assembly:single

In the target directory, you will see the following file:

	mysql-forensics-tool-1.0-jar-with-dependencies.jar	

Transfer that jar over to your application server or your on-premises
install of TeamServer.

## Usage
For basic database metrics run the following where contrast.home equals the installation directory of TeamServer:

	java -Dcontrast.home=/your/contrast/home -jar \
		mysql-forensics-tool-1.0-jar-with-dependencies.jar

TIP: Make sure your user can read the TeamServer configuration files. Otherwise, run with sudo at your own risk.

To enable advanced metrics, the mysql-sys schema needs to be initialized. If you arent familar with mysql-sys, please 
[click here](https://github.com/mysql/mysql-sys). Don't worry, we handle this for you. 

With the option performance.enabled set to true, the runnable jar will attempt to apply the SQL script to your 
database, adding a "sys" schema. If the "sys" schema is detected, nothing will be applied. To enable advanced 
metrics execute the following:

	java -Dcontrast.home=/your/contrast/home -Dperformance.enabled=true \
		-jar mysql-forensics-tool-1.0-jar-with-dependencies.jar

For some reason if we cannot your mysql client utility, add the following argument to your command:

	-Dmysql.bin=/path/to/your/mysql/client/binary

You should now see MySQL metrics in you terminal

## Example Output

Global Variables:

	310317 13.17.34,723 INFO  getVersion => {"version":"5.6.28-log"}
	310317 13.17.34,737 INFO  getVariables => {"Variable_name":"Aborted_clients","Value":"2"}
	310317 13.17.34,738 INFO  getVariables => {"Variable_name":"Aborted_connects","Value":"3"}
	310317 13.17.34,738 INFO  getVariables => {"Variable_name":"Binlog_cache_disk_use","Value":"0"}
	310317 13.17.34,738 INFO  getVariables => {"Variable_name":"Binlog_cache_use","Value":"0"}
	310317 13.17.34,738 INFO  getVariables => {"Variable_name":"Binlog_stmt_cache_disk_use","Value":"0"}
	310317 13.17.34,739 INFO  getVariables => {"Variable_name":"Binlog_stmt_cache_use","Value":"0"}
	310317 13.17.34,739 INFO  getVariables => {"Variable_name":"Bytes_received","Value":"10168073"}
	310317 13.17.34,739 INFO  getVariables => {"Variable_name":"Bytes_sent","Value":"4705435"}
	310317 13.17.34,764 INFO  getVariables => {"Variable_name":"Connection_errors_max_connections","Value":"0"}
	310317 13.17.34,777 INFO  getVariables => {"Variable_name":"Max_used_connections","Value":"3"}


Table Volumetrics:

	310317 13.17.34,865 INFO  getTableSizes => {"Table":"artifacts_java","MB":318.19,"Objects":195608576}
	310317 13.17.34,865 INFO  getTableSizes => {"Table":"vulnerabilities","MB":42.08,"Objects":2637824}
	310317 13.17.34,865 INFO  getTableSizes => {"Table":"trace_data","MB":0.19,"Objects":180224}
	310317 13.17.34,866 INFO  getTableSizes => {"Table":"notifications_messages","MB":0.16,"Objects":147456}
	310317 13.17.34,866 INFO  getTableSizes => {"Table":"servers","MB":0.16,"Objects":147456}
	310317 13.17.34,866 INFO  getTableSizes => {"Table":"organizations","MB":0.11,"Objects":98304}
	310317 13.17.34,901 INFO  getTableSizes => {"Table":"onboarding_content_images","MB":0.03,"Objects":16384}
	310317 13.17.34,908 INFO  getTableSizes => {"Table":"rules","MB":0.08,"Objects":16384}
	310317 13.17.34,908 INFO  getTableSizes => {"Table":"webhooks","MB":0.03,"Objects":16384}
	310317 13.17.34,909 INFO  getTableSizes => {"Table":"organization_remediation_policy_applications","MB":0.03,"Objects":16384}
	310317 13.17.34,909 INFO  getTableSizes => {"Table":"agent_features","MB":0.03,"Objects":16384}
	310317 13.17.34,909 INFO  getTableSizes => {"Table":"configuration_key","MB":0.03,"Objects":16384}
	310317 13.17.34,909 INFO  getTableSizes => {"Table":"user_preferences","MB":0.03,"Objects":16384}

InnoDB Metrics:

	310317 13.17.36,497 INFO  getInnodbBufferStatsByTable => {"object_schema":"mysql","object_name":"innodb_index_stats","allocated":"624.00 KiB","data":"285.27 KiB","pages":39,"pages_hashed":21,"pages_old":0,"rows_cached":2283}
	310317 13.17.36,499 INFO  getInnodbBufferStatsByTable => {"object_schema":"mysql","object_name":"innodb_table_stats","allocated":"48.00 KiB","data":"16.16 KiB","pages":3,"pages_hashed":1,"pages_old":0,"rows_cached":212}
	310317 13.17.36,523 INFO  getIoGlobalByFileByBytes => {"file":"@@datadir/mysql/innodb_index_stats.ibd","count_read":43,"total_read":"688.00 KiB","avg_read":"16.00 KiB","count_write":72,"total_written":"1.12 MiB","avg_write":"16.00 KiB","total":"1.80 MiB","write_pct":62.61}
	310317 13.17.36,523 INFO  getIoGlobalByFileByBytes => {"file":"@@datadir/mysql/innodb_table_stats.ibd","count_read":7,"total_read":"112.00 KiB","avg_read":"16.00 KiB","count_write":32,"total_written":"512.00 KiB","avg_write":"16.00 KiB","total":"624.00 KiB","write_pct":82.05}
	310317 13.17.36,531 INFO  getIoGlobalByWaitByBytes => {"event_name":"innodb/innodb_data_file","total":3134,"total_latency":"427.78 ms","min_latency":"0 ps","avg_latency":"136.50 us","max_latency":"6.70 ms","count_read":1231,"total_read":"21.20 MiB","avg_read":"17.64 KiB","count_write":551,"total_written":"14.45 MiB","avg_written":"26.86 KiB","total_requested":"35.66 MiB"}
	310317 13.17.36,531 INFO  getIoGlobalByWaitByBytes => {"event_name":"innodb/innodb_log_file","total":162,"total_latency":"57.63 ms","min_latency":"0 ps","avg_latency":"355.75 us","max_latency":"1.66 ms","count_read":6,"total_read":"68.00 KiB","avg_read":"11.33 KiB","count_write":75,"total_written":"95.50 KiB","avg_written":"1.27 KiB","total_requested":"163.50 KiB"}
	310317 13.17.36,538 INFO  getIoGlobalByWaitByLatency => {"event_name":"innodb/innodb_data_file","total":3134,"total_latency":"427.78 ms","avg_latency":"136.50 us","max_latency":"6.70 ms","read_latency":"266.79 ms","write_latency":"11.00 ms","misc_latency":"149.99 ms","count_read":1231,"total_read":"21.20 MiB","avg_read":"17.64 KiB","count_write":551,"total_written":"14.45 MiB","avg_written":"26.86 KiB"}

## Want a continuous log?

Add the following to your CRON entry with the appropriate paths (See Warnings)

	*/20 * * * * /path/to/java -Dcontrast.home=/path/to/contrast/home -Dperformance.enabled=true -jar /path/to/mysql-forensics-tool-1.0-jar-with-dependencies.jar > /path/to/contrast/home/logs/mysql_forensics.log

## Warnings
* Running this against your database may decrease performance
* This jar does add a schema to your database

## Contributing
1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
## Credits
Thanks to Jeff Whalen.
