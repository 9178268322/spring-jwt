package com.wbl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbl.dao.AccountRepository;
import com.wbl.models.Account;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllAccounts() {

		return (List<Account>) accountRepository.findAll();

	}

	public Account getAccount(Long id) {

		return accountRepository.findByid(id);
	}

	public Account addAccount(Account ac) {
		return accountRepository.save(ac);

	}

	public Account updateAccount(Account ac, Long id) {
		return accountRepository.save(ac);
	}

	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);

	}

}
