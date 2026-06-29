package WalletServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.User;
import walletService.WalletService;

public class WaleetServiceImpl implements WalletService{

	//____________________________________________________________________________
	
	private DataSource datasource ;
	public WaleetServiceImpl(DataSource datasource) {
		this.datasource = datasource;
		
	}
	//____________________________________________________________________________
	
	    
	@Override
	public double getBalance(int userId) {

	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {

	        connection = datasource.getConnection();

	        String sql = "SELECT BALANCE FROM WALLETS WHERE USER_ID=?";

	        statement = connection.prepareStatement(sql);

	        statement.setInt(1, userId);

	        resultSet = statement.executeQuery();

	        if(resultSet.next()) {

	            return resultSet.getDouble("BALANCE");

	        }

	    } catch (SQLException e) {

	        e.printStackTrace();

	    } finally {

	        try {

	            if(resultSet != null)
	                resultSet.close();

	            if(statement != null)
	                statement.close();

	            if(connection != null)
	                connection.close();

	        } catch (SQLException e) {

	            e.printStackTrace();

	        }

	    }

	    return 0;

	}

		
		
	@Override
	public boolean deposit(int userId, double amount, String description) {

	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {

	        if(!isValidAmount(amount))
	            return false;

	        connection = datasource.getConnection();

	        String sql =
	                "UPDATE WALLETS SET BALANCE = BALANCE + ? WHERE USER_ID=?";

	        statement = connection.prepareStatement(sql);

	        statement.setDouble(1, amount);
	        statement.setInt(2, userId);

	        int rows = statement.executeUpdate();

	        return rows > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();

	    } finally {

	        try {

	            if(statement != null)
	                statement.close();

	            if(connection != null)
	                connection.close();

	        } catch (SQLException e) {

	            e.printStackTrace();

	        }

	    }

	    return false;

	}

	@Override
	public boolean withdraw(int userId, double amount, String description) {

	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {

	        if(!isValidAmount(amount))
	            return false;

	        double balance = getBalance(userId);

	        if(balance < amount)
	            return false;

	        connection = datasource.getConnection();

	        String sql =
	                "UPDATE WALLETS SET BALANCE = BALANCE - ? WHERE USER_ID=?";

	        statement = connection.prepareStatement(sql);

	        statement.setDouble(1, amount);
	        statement.setInt(2, userId);

	        int rows = statement.executeUpdate();

	        return rows > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();

	    } finally {

	        try {

	            if(statement != null)
	                statement.close();

	            if(connection != null)
	                connection.close();

	        } catch (SQLException e) {

	            e.printStackTrace();

	        }

	    }

	    return false;

	}
	@Override
	public boolean transfer(int fromUserId,
	                        int toUserId,
	                        double amount,
	                        String description) {

	    Connection connection = null;

	    PreparedStatement withdrawStatement = null;
	    PreparedStatement depositStatement = null;

	    try {

	        if(!isValidAmount(amount))
	            return false;

	        if(getBalance(fromUserId) < amount)
	            return false;

	        connection = datasource.getConnection();

	        connection.setAutoCommit(false);

	        String withdrawSql =
	                "UPDATE WALLETS SET BALANCE = BALANCE - ? WHERE USER_ID=?";

	        withdrawStatement =
	                connection.prepareStatement(withdrawSql);

	        withdrawStatement.setDouble(1, amount);
	        withdrawStatement.setInt(2, fromUserId);

	        int rows1 = withdrawStatement.executeUpdate();

	        if(rows1 == 0){

	            connection.rollback();

	            return false;

	        }

	        String depositSql =
	                "UPDATE WALLETS SET BALANCE = BALANCE + ? WHERE USER_ID=?";

	        depositStatement =
	                connection.prepareStatement(depositSql);

	        depositStatement.setDouble(1, amount);
	        depositStatement.setInt(2, toUserId);

	        int rows2 = depositStatement.executeUpdate();

	        if(rows2 == 0){

	            connection.rollback();

	            return false;

	        }

	        connection.commit();

	        return true;

	    } catch (SQLException e) {

	        try {

	            if(connection != null)
	                connection.rollback();

	        } catch (SQLException ex) {

	            ex.printStackTrace();

	        }

	        e.printStackTrace();

	    } finally {

	        try {

	            if(withdrawStatement != null)
	                withdrawStatement.close();

	            if(depositStatement != null)
	                depositStatement.close();

	            if(connection != null)
	                connection.close();

	        } catch (SQLException e) {

	            e.printStackTrace();

	        }

	    }

	    return false;

	}

	@Override
	public User login(String first_name, String password) {

	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {

	        // Validation
	        if (first_name == null || first_name.trim().isEmpty())
	            return null;

	        if (password == null || password.trim().isEmpty())
	            return null;

	        connection = datasource.getConnection();

	        String sql =
	                "SELECT * FROM USERS_ACCOUNT WHERE FIRST_NAME = ? AND PASSWORD = ?";

	        statement = connection.prepareStatement(sql);

	        statement.setString(1, first_name);
	        statement.setString(2, password);

	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {

	            User user = new User();

	            user.setId(resultSet.getLong("ID"));
	            user.setFirst_name(resultSet.getString("FIRST_NAME"));
	            user.setLast_name(resultSet.getString("LAST_NAME"));
	            user.setPhone(resultSet.getString("PHONE"));
	            user.setPassword(resultSet.getString("PASSWORD"));

	            return user;
	        }

	    } catch (SQLException e) {

	        e.printStackTrace();

	    } finally {

	        try {

	            if (resultSet != null)
	                resultSet.close();

	            if (statement != null)
	                statement.close();

	            if (connection != null)
	                connection.close();

	        } catch (SQLException e) {

	            e.printStackTrace();
	        }
	    }

	    return null;
	}
		@Override
		public boolean signUp(User user) {

		    Connection connection = null;
		    PreparedStatement userStatement = null;
		    PreparedStatement walletStatement = null;
		    ResultSet resultSet = null;

		    try {

		        if(user == null)
		            return false;

		        if(!isValidPhone(user.getPhone()))
		            return false;

		        if(!isValidPassword(user.getPassword()))
		            return false;

		        if(userExists(user.getPhone()))
		            return false;

		        connection = datasource.getConnection();

		        connection.setAutoCommit(false);

		        String userSql =
		                "INSERT INTO USERS_ACCOUNT(FIRST_NAME,LAST_NAME,PASSWORD,PHONE) VALUES(?,?,?,?)";

		        userStatement =
		                connection.prepareStatement(userSql,new String[]{"ID"});

		        userStatement.setString(1,user.getFirst_name());
		        userStatement.setString(2,user.getLast_name());
		        userStatement.setString(3,user.getPassword());
		        userStatement.setString(4,user.getPhone());

		        int rows=userStatement.executeUpdate();

		        if(rows==0){

		            connection.rollback();
		            return false;

		        }

		        resultSet=userStatement.getGeneratedKeys();

		        if(!resultSet.next()){

		            connection.rollback();
		            return false;

		        }

		        long userId=resultSet.getLong(1);

		        String walletSql=
		                "INSERT INTO WALLETS(USER_ID,BALANCE) VALUES(?,?)";

		        walletStatement=connection.prepareStatement(walletSql);

		        walletStatement.setLong(1,userId);
		        walletStatement.setDouble(2,0);

		        int walletRows=walletStatement.executeUpdate();

		        if(walletRows==0){

		            connection.rollback();
		            return false;

		        }

		        connection.commit();

		        return true;

		    } catch (SQLException e) {

		        try {

		            if(connection!=null)
		                connection.rollback();

		        } catch (SQLException ex) {

		            ex.printStackTrace();
		        }

		        e.printStackTrace();

		    } finally {

		        try {

		            if(resultSet!=null)
		                resultSet.close();

		            if(userStatement!=null)
		                userStatement.close();

		            if(walletStatement!=null)
		                walletStatement.close();

		            if(connection!=null)
		                connection.close();

		        } catch (SQLException e) {

		            e.printStackTrace();
		        }

		    }

		    return false;
		}

		@Override
		public boolean logout(int userId) {

		    return true;

		}


		@Override
		public boolean isValidPhone(String phone) {

		    if(phone==null)
		        return false;

		    return phone.matches("^01[0125][0-9]{8}$");

		}


		@Override
		public boolean isValidPassword(String password) {

		    if(password==null)
		        return false;

		    return password.length()>=8;

		}


		@Override
		public boolean userExists(String phone) {

		    Connection connection=null;
		    PreparedStatement statement=null;
		    ResultSet resultSet=null;

		    try{

		        connection=datasource.getConnection();

		        String sql=
		                "SELECT ID FROM USERS_ACCOUNT WHERE PHONE=?";

		        statement=connection.prepareStatement(sql);

		        statement.setString(1,phone);

		        resultSet=statement.executeQuery();

		        return resultSet.next();

		    }catch(SQLException e){

		        e.printStackTrace();

		    }finally{

		        try{

		            if(resultSet!=null)
		                resultSet.close();

		            if(statement!=null)
		                statement.close();

		            if(connection!=null)
		                connection.close();

		        }catch(SQLException e){

		            e.printStackTrace();

		        }

		    }

		    return false;

		}


		@Override
		public boolean isValidAmount(double amount) {

		    return amount>0;

		}
		
		
		
		@Override
		public User findByPhone(String phone) {

		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet resultSet = null;

		    try {

		        if (phone == null || phone.trim().isEmpty())
		            return null;

		        connection = datasource.getConnection();

		        String sql =
		                "SELECT * FROM USERS_ACCOUNT WHERE PHONE = ?";

		        statement = connection.prepareStatement(sql);

		        statement.setString(1, phone);

		        resultSet = statement.executeQuery();

		        if (resultSet.next()) {

		            User user = new User();

		            user.setId(resultSet.getLong("ID"));
		            user.setFirst_name(resultSet.getString("FIRST_NAME"));
		            user.setLast_name(resultSet.getString("LAST_NAME"));
		            user.setPhone(resultSet.getString("PHONE"));
		            user.setPassword(resultSet.getString("PASSWORD"));

		            return user;

		        }

		    } catch (SQLException e) {

		        e.printStackTrace();

		    } finally {

		        try {

		            if (resultSet != null)
		                resultSet.close();

		            if (statement != null)
		                statement.close();

		            if (connection != null)
		                connection.close();

		        } catch (SQLException e) {

		            e.printStackTrace();

		        }

		    }

		    return null;

		}

	    
	    

}
