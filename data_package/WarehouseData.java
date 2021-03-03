package data;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;

public class WarehouseData {
	protected static final Logger LOGGER = Logger.getLogger(WarehouseData.class.getName());
	private Connection connection;
	private Statement statement;
	
	public WarehouseData() {}
	
	public void createDatabase() throws SQLException {
		String query = "create database if not exists warehouse;";
		
		connection = ConnectionFactory.getConnection();
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "WarehouseDAO : " + e.getMessage());
			throw e;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
}
