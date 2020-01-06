package com.wbl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbl.models.Account;
import com.wbl.service.AccountService;

@RestController
@RequestMapping("api/v1")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
	}

	@GetMapping("/accounts/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable Long id) {
		return new ResponseEntity<Account>(accountService.getAccount(id), HttpStatus.OK);
	}

	@PostMapping("/accounts")
	public ResponseEntity<Account> addAccount(@RequestBody Account ac) {
		return new ResponseEntity<Account>(accountService.addAccount(ac), HttpStatus.CREATED);
	}

	@PutMapping("/accounts/{id}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account ac, @PathVariable Long id) {
		return new ResponseEntity<Account>(accountService.updateAccount(ac, id), HttpStatus.OK);
	}

	@DeleteMapping("/accounts/{id}")
	public ResponseEntity deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
