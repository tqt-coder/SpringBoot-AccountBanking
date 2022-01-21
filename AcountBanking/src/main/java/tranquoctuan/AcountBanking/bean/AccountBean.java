package tranquoctuan.AcountBanking.bean;
public class AccountBean {
	private int id;
	private String fullName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	private double balance;
	public AccountBean(int id, String fullName, double balance) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.balance = balance;
	}
	public AccountBean() {
		super();
	}

}