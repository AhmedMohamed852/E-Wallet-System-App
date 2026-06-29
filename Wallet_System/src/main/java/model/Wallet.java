package model;

public class Wallet {
	
	private Long id;
	

	private Long user_id;
	
	private double balance;
	
	
	
	
	
	
	public Wallet(Long id, Long user_id, double balance) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.balance = balance;
	}






	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public Long getUser_id() {
		return user_id;
	}






	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}






	public double getBalance() {
		return balance;
	}






	public void setBalance(double balance) {
		this.balance = balance;
	}






	public Wallet() {
		
	}

}
