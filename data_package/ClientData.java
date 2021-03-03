package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.pdf.PdfPTable;

import connection.ConnectionFactory;
import model.Client;

public class ClientData {
	protected static final Logger LOGGER = Logger.getLogger(WarehouseData.class.getName());
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private ResultSetMetaData resultSetMD;

	public ClientData() {
	}

	public void insertClient(Client client) throws SQLException {
		String query = "insert ignore into client(name, adress) values ( xname?x, xadress?x);";

		query = query.replace('x', '"');
		query = query.replace("name?", client.getName());
		query = query.replace("adress?", client.getAdress());

		System.out.println(query);

		connection = ConnectionFactory.getConnection();

		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : insertClient : " + e.getMessage());
			throw e;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public void deleteClient(String name, String adress) throws SQLException {
		String query = "delete from client where client.name = xname?x and client.adress = xadress?x;";

		query = query.replace('x', '"');
		query = query.replace("name?", name);
		query = query.replace("adress?", adress);

		System.out.println(query);

		connection = ConnectionFactory.getConnection();

		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : deleteClient :" + e.getMessage());
			throw e;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public PdfPTable selectClients(){
		String query = "select * from client;";

		System.out.println(query);

		connection = ConnectionFactory.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery(query);
			resultSetMD = resultSet.getMetaData();

			PdfPTable bill = new PdfPTable(resultSetMD.getColumnCount());

			for (int i = 1; i <= resultSetMD.getColumnCount(); ++i) {
				bill.addCell(resultSetMD.getColumnName(i));
			}

			@SuppressWarnings("unused")
			int rows = 0;
			while (resultSet.next()) {
				for (int i = 1; i <= resultSetMD.getColumnCount(); ++i) {
					bill.addCell(resultSet.getString(resultSetMD.getColumnName(i)));
				}
				rows++;
			}
			return bill;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Select : ClientDAO : " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
}
