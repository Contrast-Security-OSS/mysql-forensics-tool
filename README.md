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

	mvn clean compile package

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
