package com.wbl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserInfo {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "user_name", nullable = false)
	private String username;
	
	@Column(name = "pass_word", nullable = false)
	private String password;
	
}
