package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Provides access to the database
 * Created on 8/31/16.
 *
 * @author pwaite
 * @author Alex M - Fall 2019 - added multi-line sql capability
 */

public class Database {

    // create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;
    private Connection connection;
    private Logger logger = LogManager.getLogger(this.getClass());

    /** private constructor prevents instantiating this class anywhere else
	**/
    private Database() {
        loadProperties();

    }

    /** load the properties file containing the driver, connection url, userid and pwd.
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioe) {
            logger.info("Database.loadProperties()...Cannot load the properties file");
        } catch (Exception e) {
            logger.info("Database.loadProperties()...{}", String.valueOf(e));
        }

    }

    /** get the only Database object available
        @return the single database object
    */
    public static Database getInstance() {
        return instance;
    }

    /** get the database connection
        @return the database connection
    */
    public Connection getConnection() {
        return connection;
    }
  
    /** attempt to connect to the database
    */
    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
    }

    /** close and clean up the database connection
    */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.info("Cannot close connection{}", String.valueOf(e));
            }
        }

        connection = null;
    }

    /**
     * Run the sql.
     *
     * @param sqlFile the sql file to be read and executed line by line
     */
    public void runSQL(String sqlFile) {

        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(sqlFile))))  {

            connect();
            stmt = connection.createStatement();

            String sql = "";
            while (br.ready())
            {
                char inputValue = (char)br.read();

                if(inputValue == ';')
                {
                    stmt.executeUpdate(sql);
                    sql = "";
                }
                else
                    sql += inputValue;
            }

        } catch (SQLException se) {
            logger.info("SQL Exception{}", String.valueOf(se));
        } catch (Exception e) {
            logger.info("Exception{}", String.valueOf(e));
        } finally {
            disconnect();
        }

    }
}
