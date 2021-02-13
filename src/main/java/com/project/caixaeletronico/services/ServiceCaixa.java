package com.project.caixaeletronico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.caixaeletronico.entities.CaixaEntitie;
import com.project.caixaeletronico.models.ObjProducer;
import com.project.caixaeletronico.repositories.RepositorieCaixa;

@Service
public class ServiceCaixa {

	@Autowired
	RepositorieCaixa repo;

	@Autowired
	ObjProducer objProd;

	public boolean checkUsers(String user, String password) {

		try {
			return (repo.checkUser(user) != null) && (repo.checkUserPass(user, password) != null);

		} catch (Exception e) {
			System.out.println(String.format("Erro ao buscar no banco de dados: %s", e.getMessage()));
			return false;
		}

	}

	public Double checkBalances(ObjProducer objProd) {
		return repo.checkBalance(objProd.getUser());

	}

	public String checkRetirementMoth(String user) {

		try {

			objProd.setBalance(repo.checkBalance(user));
			objProd.setMothsRetirement(repo.checkMoths(user));
			Double valueMouth = (objProd.getBalance() / objProd.getMothsRetirement());

			return String.format("%.2f", valueMouth);
		} catch (Exception e) {
			System.out.println(String.format("Erro ao efetuar procedimento %s", e.getMessage()));// trocar pra log
			return "0";
		}

	}

	public Boolean registerUser(ObjProducer objProd) {

		CaixaEntitie entity = new CaixaEntitie();
		entity.setUser(objProd.getUser());
		entity.setPassword(objProd.getPassword());
		entity.setCpf(objProd.getCpf());
		entity.setEmail(objProd.getEmail());
		entity.setSaldo(objProd.getBalance());
		entity.setMothsRetirement(objProd.getMothsRetirement());

		try {
			repo.save(entity);
			return true;
		} catch (Exception e) {
			System.out.println(String.format("Erro ao savar no banco de dados: %s", e.getMessage()));// trocar pra log
			return false;
		}
	}

	public Object checkMoth(String user) {

		try {

			objProd.setMothsRetirement(repo.checkMoths(user));
			return objProd.getMothsRetirement();

		} catch (Exception e) {
			System.out.println(String.format("Erro ao buscar no banco de dados: %s", e.getMessage()));// trocar pra log
			return false;
		}
	}

}
