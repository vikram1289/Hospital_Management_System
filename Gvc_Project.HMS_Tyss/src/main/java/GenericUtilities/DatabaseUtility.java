package GenericUtilities;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;




public class DatabaseUtility {
Connection connection = null;
/**
 * This method will register Driver and create connection
 * @author Vikram
 * @return void
 * @throws SQLException
 */
public void registerDriver() throws SQLException {
	Driver driver = new com.mysql.jdbc.Driver();
	DriverManager.registerDriver(driver);
	connection = DriverManager.getConnection(IPathConstants.dbURL, IPathConstants.dbUsername, IPathConstants.dbPassword);
}
/**
 * This method will execute query and return result
 * @author Vikram
 * @param query
 * @param expectedResult
 * @param column
 * @param columnIndex
 * @return String
 * @throws SQLException
 */
public String executeQuery(String query, String expectedResult, int columnIndex) throws SQLException {
	boolean flag = false;
	ResultSet result = connection.createStatement().executeQuery(query);
	while(result.next()) {
		if(result.getString(columnIndex).equalsIgnoreCase(expectedResult)) {
			System.out.println("Query executed successfully");
			flag = true;
		}else {
			System.out.println("Query not executed");
		}
	}
	if(flag) {
	return expectedResult;
	}else {
		return "";
	}
}
/**
 * This method will take query and update database
 * @author vikram
 * @return void
 * @throws SQLException
 */
public void executeUpdate(String query) throws SQLException {
	int result = connection.createStatement().executeUpdate(query);
	if(result>0) {
		System.out.println("Table Updated Successfully");
	}else {
		System.out.println("Table not updated");
	}
}
/**
 * This method will close database connection
 * author vikram
 * @return void
 * @throws SQLException
 */
	public void closeDbConnection() throws SQLException{
		connection.close();
	}
}
