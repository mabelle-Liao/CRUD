package net.mabelle.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Timestamp;


import net.mabelle.crud.model.Admin;

public class AdminDAO {
	//private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cruddemo?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASSWORD = "j98619861";
	String input = "2020-05-07T10:05:05.301011" ;
	LocalDateTime ldt = LocalDateTime.parse( input ) ;

	private static final String INSERT_USERS_SQL = "INSERT INTO admin"
			+ "  (workNum,passWord,name, role,token, authority,stamp) VALUES " + " (?, ?, ?,?, ?, ?,?);";

	private static final String SELECT_USER_BY_ID = "select id, workNum, passWord, name, role, token, authority, stamp from admin where id =?";
	private static final String SELECT_ALL_USERS = "select * from admin";
	private static final String DELETE_USERS_SQL = "delete from admin where id = ?;";
	private static final String UPDATE_USERS_SQL = "update admin set workNum = ?, passWord = ?, name = ?, role= ?, token =?, authority=?, stamp=? where id = ?;";

	public AdminDAO() {
	}
	protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
	
	public void insertUser(Admin admin) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, admin.getWorkNum());
            preparedStatement.setString(2, admin.getPassWord());
            preparedStatement.setString(3, admin.getName());
            preparedStatement.setString(4, admin.getRole());
            preparedStatement.setString(5, admin.getToken());
            preparedStatement.setString(6, admin.getAuthority());
            preparedStatement.setObject(7, ldt);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
	
	public Admin selectUser(int id) {
		Admin admin = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String workNum = rs.getString("workNum");
                String passWord = rs.getString("passWord");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String token = rs.getString("token");
                String authority = rs.getString("authority");
                
                Timestamp timeStampObj = rs.getTimestamp("stamp");
                String timeStamp = timeStampObj.toString();
                admin = new Admin(id, workNum,passWord,name, role,token, authority,timeStamp);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return admin;
    }
	
	public List < Admin > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Admin > admins = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String workNum = rs.getString("workNum");
                String passWord = rs.getString("passWord");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String token = rs.getString("token");
                String authority = rs.getString("authority");
                
                Timestamp timeStampObj = rs.getTimestamp("stamp");
                String timeStamp = timeStampObj.toString();
                admins.add(new Admin(id, workNum,passWord,name, role,token, authority,timeStamp));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return admins;
    }
	
	public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(Admin admin) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, admin.getWorkNum());
            statement.setString(2, admin.getPassWord());
            statement.setString(3, admin.getName());
            statement.setString(4, admin.getRole());
            statement.setString(5, admin.getToken());
            statement.setString(6, admin.getAuthority());
            statement.setObject(7, ldt);
            statement.setInt(8, admin.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
