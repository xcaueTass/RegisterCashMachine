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

	public ObjProducer() {
		super();
	}

	public ObjProducer(String user, String password, String cpf, String email, Double balance, int mothsRetirement) {
		super();
		this.user = user;
		this.password = password;
		this.cpf = cpf;
		this.email = email;
		this.balance = balance;
		this.mothsRetirement = mothsRetirement;
	}

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

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
