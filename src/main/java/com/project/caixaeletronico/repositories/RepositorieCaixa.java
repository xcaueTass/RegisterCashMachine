package com.project.caixaeletronico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.caixaeletronico.entities.CaixaEntitie;

@Repository
public interface RepositorieCaixa extends JpaRepository<CaixaEntitie, Long> {

	@Query(value = "SELECT NAME FROM USER_TABLE where NAME = :user", nativeQuery = true)
	String checkUser(String user);

	@Query(value = "SELECT NAME, PASSWORD FROM USER_TABLE where NAME = :user AND PASSWORD = :password", nativeQuery = true)
	String checkUserPass(String user, String password);

	@Query(value = "SELECT BALANCE FROM USER_TABLE where NAME = :user", nativeQuery = true)
	Double checkBalance(String user);

	@Query(value = "SELECT MOTH_RETIREMENT FROM USER_TABLE where NAME = :user", nativeQuery = true)
	int checkMoths(String user);

}
