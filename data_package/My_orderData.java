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
import model.My_order;

public class My_orderData {
	protected static final Logger LOGGER = Logger.getLogger(WarehouseData.class.getName());
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private ResultSetMetaData resultSetMD;

	public My_orderData() {
	}

	public void insertOrder(My_order order) throws SQLException {
		String query = "insert ignore into my_order(id, client_name, product_name, product_quantity) values (id?, xclient_name?x, xproduct_name?x, product_quantity?);";

		query = query.replace('x', '"');
		query = query.replace("id?", order.getId());
		query = query.replace("client_name?", order.getClient_name());
		query = query.replace("product_name?", order.getProduct_name());
		query = query.replace("product_quantity?", order.getProduct_quantity());

		System.out.println(query);

		connection = ConnectionFactory.getConnection();

		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "My_orderDAO : insertOrder : " + e.getMessage());
			throw e;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public PdfPTable selectOrders(){
		String query = "select * from my_order;";

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
			LOGGER.log(Level.WARNING, "Select : My_orderDAO : " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
}
