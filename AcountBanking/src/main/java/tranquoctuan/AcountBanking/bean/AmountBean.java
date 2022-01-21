package tranquoctuan.AcountBanking.bean;

public class AmountBean {
	private int idSend;
	private int idReceive;
	private double amount;
	public int getIdSend() {
		return idSend;
	}
	public void setIdSend(int idSend) {
		this.idSend = idSend;
	}
	public int getIdReceive() {
		return idReceive;
	}
	public void setIdReceive(int idReceive) {
		this.idReceive = idReceive;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public AmountBean(int idSend, int idReceive, double amount) {
		super();
		this.idSend = idSend;
		this.idReceive = idReceive;
		this.amount = amount;
	}
	public AmountBean() {
		super();
	}
	

}
