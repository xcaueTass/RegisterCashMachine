package com.project.caixaeletronico.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.caixaeletronico.CaixaeletronicoApplication;
import com.project.caixaeletronico.configs.DataSourceConfig;
import com.project.caixaeletronico.entities.CaixaEntity;
import com.project.caixaeletronico.models.ObjProducer;
import com.project.caixaeletronico.repositories.RepositorieCaixa;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CaixaeletronicoApplication.class, DataSourceConfig.class })
class ServiceRuleTest {

	@Autowired
	RepositorieCaixa repo;

	@Autowired
	ObjProducer obj;

	@Autowired
	ServiceCaixa service;

	private static final Logger logger = LoggerFactory.getLogger(ServiceRuleTest.class);

	@BeforeEach
	public void init() {

		logger.info("Deletando dados da tabela para testes");
		repo.deleteAll();

		logger.info("Inserindo dados na entidade");
		try {

			CaixaEntity entity = new CaixaEntity();
			entity.setUser("CaueTeste");
			entity.setPassword("senhaTeste");
			entity.setEmail("testeEmail@teste.com");
			entity.setCpf("000000000000");
			entity.setSaldo(200.00);
			entity.setMothsRetirement(8);

			logger.info("Salvando na tabela de testes");
			repo.save(entity);

			logger.info("Dados salvo com sucesso");

		} catch (Exception e) {
			logger.error("Erro ao salvar dados: " + e.getMessage());
		}
	}

	@Test
	void testRepositoryRules() {
		String user = "CaueTeste";

		logger.info("Efetuando validações do repositorio");
		try {
			assertNotNull(repo.findById((long) 1));
			assertNotNull(repo.checkUser(user));
			assertNotNull(repo.checkBalance(user));

			assertEquals(repo.checkUser(user), user);
			assertNotEquals(0, repo.checkMoths(user));
			assertNotEquals(0.00, repo.checkBalance(user));

			logger.info("Repositorio validado - OK");

		} catch (Exception e) {
			logger.error("Erro ao efetuar as validações de repositório: " + e.getMessage());
		}

	}

	@Test
	void testServiceRules() {
		String user = "CaueTeste";
		String password = "senhaTeste";

		logger.info("Efetuando validações da regra de negócio");
		try {

			assertTrue(service.checkUsers(user, password));
			assertNotEquals("0.00", service.checkRetirementMoth(user));
			assertNotEquals("0", service.checkRetirementMoth(user));
			assertNotEquals(0, service.checkMoth(user));

			logger.info("Regra de negócio validada - OK");

		} catch (Exception e) {
			logger.error("Erro ao efetuar as validações de regra de negócio: " + e.getMessage());
		}

	}

}
