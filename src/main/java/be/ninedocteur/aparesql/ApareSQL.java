package be.ninedocteur.aparesql;

import be.ninedocteur.aparesql.database.Database;
import be.ninedocteur.aparesql.database.DatabaseGetter;
import be.ninedocteur.aparesql.utils.MessageUtils;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ApareSQL {

    public void getDatabase(){
        Database.getDatabase();
    }

    private Connection connection;
    private Statement statement;
    DatabaseGetter database = Database.getDatabase();

    /**
     * Constructor.
     * @param host The host of the MySQL database.
     * @param user The user of the MySQL database.
     * @param password The password of the MySQL database.
     */
    public ApareSQL(String host, String user, String password) {
        database.setDatabaseHost(host);
        database.setDatabaseUser(user);
        database.setDatabasePassword(password);
        MessageUtils.sendWarn("Please use this lib only for private use. MySQL Database auth information can be easily get when decompiling the jar file.");
    }

    /**
     * Connect to the MySQL database.
     * @param name The name of the database.
     * @return The database.
     */
    public Database connectToDatabase(String name) {
        System.out.println("Connecting to database " + name + "...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + database.getDatabaseHost() + ":3306/" + name, database.getDatabaseUser(), database.getDatabasePassword());
            statement = connection.createStatement();
            System.out.println("Connected to database " + name + ".");
        } catch (ClassNotFoundException e) {
            System.err.println("JBDC driver not found. Please install it.");
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database " + name + ":\n" + e.getMessage());
        }
        return new Database(name, statement);
    }

    /**
     * Close the connection to the MySQL database.
     */
    public void close(){
        System.out.println("Closing connection...");
        try {
            connection.close();
            statement.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.err.println("Failed to close the connection. Be sure to be connected to the database! :\n" + e.getMessage());
            System.exit(1);
        }
    }
}
