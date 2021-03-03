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
import model.Product;

public class ProductData {
	protected static final Logger LOGGER = Logger.getLogger(WarehouseData.class.getName());
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private ResultSetMetaData resultSetMD;

	public ProductData() {
	}

	public void insertProduct(Product product) throws SQLException {
		String query = "insert ignore into product(name, quantity, price) values ( xname?x, quantity?, price?);";

		query = query.replace('x', '"');
		query = query.replace("name?", product.getName());
		query = query.replace("quantity?", product.getQuantity());
		query = query.replace("price?", product.getPrice());

		System.out.println(query);

		connection = ConnectionFactory.getConnection();

		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : insertProduct : " + e.getMessage());
			throw e;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	public void deleteProduct(String name) throws SQLException {
		String query = "delete from product where product.name = xname?x;";

		query = query.replace('x', '"');
		query = query.replace("name?", name);

		System.out.println(query);

		connection = ConnectionFactory.getConnection();

		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : deleteProduct :" + e.getMessage());
			throw e;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public PdfPTable selectProducts(){
		String query = "select * from product;";

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
			LOGGER.log(Level.WARNING, "Select : ProductDAO : " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
}
