package com.project.caixaeletronico.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.caixaeletronico.entities.CaixaEntity;
import com.project.caixaeletronico.models.ObjProducer;
import com.project.caixaeletronico.repositories.RepositorieCaixa;

@Service
public class ServiceCaixa {

	@Autowired
	RepositorieCaixa repo;

	@Autowired
	ObjProducer objProd;

	private static final Logger logger = LoggerFactory.getLogger(ServiceCaixa.class);

	public boolean checkUsers(String user, String password) {

		try {
			logger.info("Validando se existe usuário");
			return (repo.checkUser(user) != null) && (repo.checkUserPass(user, password) != null);

		} catch (Exception e) {
			logger.info(String.format("Erro ao buscar no banco de dados: %s", e.getMessage()));
			return false;
		}

	}

	public String checkRetirementMoth(String user) {

		try {

			logger.info("Validando Saldo");
			objProd.setBalance(repo.checkBalance(user));

			logger.info("Validando meses à receber");
			objProd.setMothsRetirement(repo.checkMoths(user));
			Double valueMouth = (objProd.getBalance() / objProd.getMothsRetirement());

			logger.info("Retornando valor e os meses à receber");
			return String.format("%.2f", valueMouth);

		} catch (Exception e) {
			logger.info(String.format("Erro ao efetuar procedimento %s", e.getMessage()));
			return "0";
		}

	}

	public Boolean registerUser(ObjProducer objProd) {

		logger.info("Inserindo novo registro na tabela");
		CaixaEntity entity = new CaixaEntity();
		entity.setUser(objProd.getUser());
		entity.setPassword(objProd.getPassword());
		entity.setCpf(objProd.getCpf());
		entity.setEmail(objProd.getEmail());
		entity.setSaldo(objProd.getBalance());
		entity.setMothsRetirement(objProd.getMothsRetirement());

		try {

			repo.save(entity);
			logger.info("Dados salvo com sucesso");

			return true;
		} catch (Exception e) {
			logger.info(String.format("Erro ao savar no banco de dados: %s", e.getMessage()));
			return false;
		}
	}

	public Object checkMoth(String user) {

		try {

			logger.info("Validando meses restantes à receber");
			objProd.setMothsRetirement(repo.checkMoths(user));
			return objProd.getMothsRetirement();

		} catch (Exception e) {
			logger.info(String.format("Erro ao buscar no banco de dados: %s", e.getMessage()));
			return false;
		}
	}

}
