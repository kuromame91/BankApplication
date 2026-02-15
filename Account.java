package benkyou.exam03;

public class Account {
	private String accountNum;
	private String owner;
	private int balance;
	
	public Account(String accountNum, String owner, int balance) {
		this.accountNum = accountNum;
		this.owner = owner;
		this.balance = balance;
	}
	
	public String getAccountNum() {
		return accountNum;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void SetBalance(int balance) {
		this.balance = balance;
	}
}
