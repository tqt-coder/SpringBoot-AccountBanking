package tranquoctuan.AcountBanking.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions.SetOptions.Propagation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tranquoctuan.AcountBanking.bean.AccountBean;
import tranquoctuan.AcountBanking.entity.AccountEntity;
import tranquoctuan.AcountBanking.exception.BankTransactionException;

@Repository
@Transactional
public class BankDAO {
	@Autowired
	private EntityManager entityManage;
	public BankDAO() {
		
	}
	
	public AccountEntity findById(int id) {
		return this.entityManage.find(AccountEntity.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountBean>listAccount(){
		String sql = "Select new "+ AccountBean.class.getName() + " (e.id, e.fullName, e.balance) "+
	" from "+ AccountEntity.class.getName() + " e";
	Query query = entityManage.createQuery(sql,AccountBean.class);
	
	return 	query.getResultList();			
	}
	@Transactional(rollbackFor = BankTransactionException.class)
	public void sendMoney(int idSend, int idReceive, double amount) throws BankTransactionException{
		
		addAmount(idReceive, amount);
		addAmount(idSend, -amount);
	}

	@Transactional()
	private void addAmount(int id, double amount) throws BankTransactionException{
		// TODO Auto-generated method stub
		
		AccountEntity account = this.findById(id);
		if(account == null) {
			throw new BankTransactionException("The account not found "+ id);
		}
		double temp = account.getBalance() + amount ;
		if(temp < 0) {
			throw new BankTransactionException("The money in account "+ id+ " is enough("+ account.getBalance()+" )");
		}
		account.setBalance(temp);
	}

}
