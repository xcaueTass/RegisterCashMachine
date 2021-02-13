package com.project.caixaeletronico.models;

import org.springframework.stereotype.Component;

@Component
public class ObjProducer {

	private String user;
	private String password;
	private String cpf;
	private String email;
	private Double balance;
	private int mothsRetirement;

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public int getMothsRetirement() {
		return mothsRetirement;
	}

	public void setMothsRetirement(int yearsRetirement) {
		this.mothsRetirement = yearsRetirement;
	}

}
