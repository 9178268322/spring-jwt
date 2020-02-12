package com.wbl.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.wbl.exception.ResourceNotFoundException;
import com.wbl.models.Account;
import com.wbl.service.AccountService;

@RestController
@RequestMapping("${ac.baseurl}")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("${ac.accounts}")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
	}

	@GetMapping("${ac.account_by_id}")
	public ResponseEntity<Account> getAccount(@PathVariable Long id) throws ResourceNotFoundException {
		Account ac = accountService.getAccount(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
		return ResponseEntity.ok().body(ac);
	}

	@PostMapping("${ac.create_account}")
	public ResponseEntity<Account> addAccount(@Valid @RequestBody Account ac) {
		return new ResponseEntity<Account>(accountService.addAccount(ac), HttpStatus.CREATED);
	}

	@PutMapping("${ac.update_account}")
	public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account acDetails, @PathVariable Long id) throws ResourceNotFoundException {
		Account ac = accountService.getAccount(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
		
		ac.setAccName(acDetails.getAccName());
		ac.setAccType(acDetails.getAccType());
		ac.setBalance(acDetails.getBalance());
		ac.setUpdatedAt(new Date());
		ac.setUpdatedBy(acDetails.getUpdatedBy());
		final Account updatedAccount = accountService.addAccount(ac);
		return ResponseEntity.ok(updatedAccount);
	}

	@DeleteMapping("${ac.delete_account}")
	public Map<String, Boolean> deleteAccount(@PathVariable Long id) throws ResourceNotFoundException {
		Account ac = accountService.getAccount(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
		accountService.deleteAccount(ac);
		Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}
}
