package com.project.caixaeletronico.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class CaixaEntitie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false)
	private long codUser;

	@Column(name = "NAME", length = 50, nullable = false)
	private String user;

	@Column(name = "PASSWORD", length = 64, nullable = false)
	private String password;

	@Column(name = "CPF", length = 64, nullable = false)
	private String cpf;

	@Column(name = "E_MAIL", length = 64, nullable = false)
	private String email;

	@Column(name = "BALANCE", nullable = false)
	private Double saldo;

	@Column(name = "MOTH_RETIREMENT", nullable = false)
	private int mothsRetirement;

	public long getCodUser() {
		return codUser;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setCodUser(long codUser) {
		this.codUser = codUser;
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

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public int getMothsRetirement() {
		return mothsRetirement;
	}

	public void setMothsRetirement(int mothsRetirement) {
		this.mothsRetirement = mothsRetirement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codUser ^ (codUser >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaixaEntitie other = (CaixaEntitie) obj;
		if (codUser != other.codUser)
			return false;
		return true;
	}

}
