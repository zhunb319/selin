package com.selin.store.account.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.selin.store.account.dao.api.IAccountDao;
import com.selin.store.account.entity.Account;
import com.selin.store.account.entity.AccountVo;
import com.selin.store.account.service.api.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
	private IAccountDao accountDao;

	public Serializable save(Account account){
		return accountDao.save(account);
	}

	public void delete(Account account){
		accountDao.delete(account);
	}
	
	public void deleteByExample(Account account){
		accountDao.deleteByExample(account);
	}

	public void update(Account account){
		accountDao.update(account);
	}
	
	public void updateIgnoreNull(Account account){
		accountDao.updateIgnoreNull(account);
	}
		
	public void updateByExample(Account account){
		accountDao.update("updateByExampleAccount", account);
	}

	public AccountVo load(Account account){
		return (AccountVo)accountDao.reload(account);
	}
	
	public AccountVo selectForObject(Account account){
		return (AccountVo)accountDao.selectForObject("selectAccount",account);
	}
	
	public List<AccountVo> selectForList(Account account){
		return (List<AccountVo>)accountDao.selectForList("selectAccount",account);
	}
	
	public Page page(Page page, Account account) {
		return accountDao.page(page, account);
	}

	@Override
	public void createByUserid(Long userId) {
		Account account = new Account();
		account.setBalance(0.0);
		account.setMax_debt(0.0);
		account.setUser_id(userId);

		this.save(account);
	}

	@Autowired
	public void setIAccountDao(
			@Qualifier("accountDao") IAccountDao  accountDao) {
		this.accountDao = accountDao;
	}
	

}
