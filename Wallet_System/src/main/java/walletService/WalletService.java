package walletService;

import model.User;

public interface WalletService {

	

	    // ============================================
	    // USER MANAGEMENT
	    // ============================================

	    // Takes: first_name (String), password (String)
	    // Returns: User object
	    User login(String first_name, String password) throws Exception;

	    // Takes: first_name (String), last_name (String), password (String), phone (String)
	    // Returns: boolean (true if success)
	    boolean signUp(User user) throws Exception;

	    // Takes: userId (int)
	    // Returns: boolean (true if success)
	    boolean logout(int userId) throws Exception;

	    

	    User findByPhone(String phone) throws Exception;


	    // ============================================
	    // BALANCE OPERATIONS
	    // ============================================

	    // Takes: userId (int)
	    // Returns: double (balance amount)
	    double getBalance(int userId) throws Exception;

	   

	    // ============================================
	    // TRANSACTIONS
	    // ============================================

	    // Takes: userId (int), amount (double), description (String)
	    // Returns: boolean (true if success)
	    boolean deposit(int userId, double amount, String description) throws Exception;

	    // Takes: userId (int), amount (double), description (String)
	    // Returns: boolean (true if success)
	    boolean withdraw(int userId, double amount, String description) throws Exception;

	    // Takes: fromUserId (int), toUserId (int), amount (double), description (String)
	    // Returns: boolean (true if success)
	    boolean transfer(int fromUserId, int toUserId, double amount, String description) throws Exception;
/*
	    // Takes: userId (int)
	    // Returns: List<Transaction>
	    List<Transaction> getTransactionHistory(int userId) throws Exception;

	    // Takes: userId (int), limit (int)
	    // Returns: List<Transaction>
	    List<Transaction> getTransactionHistory(int userId, int limit) throws Exception;

	    // Takes: transactionId (int)
	    // Returns: Transaction object
	    Transaction getTransaction(int transactionId) throws Exception;
*/

	    // ============================================
	    // VALIDATION
	    // ============================================

	    // Takes: phone (String)
	    // Returns: boolean (true if valid)
	    boolean isValidPhone(String phone);

	    // Takes: password (String)
	    // Returns: boolean (true if valid)
	    boolean isValidPassword(String password);

	    // Takes: first_name (String)
	    // Returns: boolean (true if exists)
	    boolean userExists(String first_name) throws Exception;

	    // Takes: amount (double)
	    // Returns: boolean (true if valid)
	    boolean isValidAmount(double amount);

	
}
