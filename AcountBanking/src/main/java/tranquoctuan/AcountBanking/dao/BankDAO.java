package tranquoctuan.AcountBanking.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tranquoctuan.AcountBanking.bean.AccountBean;
import tranquoctuan.AcountBanking.entity.AccountEntity;

@Repository
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

}
