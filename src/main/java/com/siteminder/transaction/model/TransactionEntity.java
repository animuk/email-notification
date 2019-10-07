package com.siteminder.transaction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class TransactionEntity {
	
	
	public TransactionEntity() {}
	
	public TransactionEntity(@NotNull String from,@NotNull String to,String subject,String content) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String from;
	
	@NotNull
	private String to;
	
	private String subject;
	private String content;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public @NotNull String getFrom() {
		return from;
	}

	public void setFrom(@NotNull String from) {
		this.from = from;
	}

	public @NotNull String getTo() {
		return to;
	}

	public void setTo(@NotNull String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	

}
