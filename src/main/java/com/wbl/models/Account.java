package com.wbl.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "account_tb")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Account type can't be null")
	@Size(min = 2, message = "Account type must not be less than 2 characters")
	@Column(name = "acc_type")
	private String accType;

	@Column(name = "balance")
	private double balance;

	@NotNull(message = "Account name can't be null")
	@Size(min = 2, message = "Account name must not be less than 2 characters")
	@Column(name = "acc_name")
	private String accName;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@NotNull(message = "Created by can't be null")
	@Size(min = 2, message = "Created by must not be less than 2 characters")
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@NotNull(message = "Updated by can't be null")
	@Size(min = 2, message = "Updated by must not be less than 2 characters")
	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;
}