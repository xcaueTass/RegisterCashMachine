package com.project.caixaeletronico.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.caixaeletronico.exceptions.ErrorsMsg;
import com.project.caixaeletronico.models.ObjProducer;
import com.project.caixaeletronico.presenters.DataPresenter;
import com.project.caixaeletronico.services.ServiceCaixa;

@RestController
@RequestMapping(value = "/cashMachine")
public class ControllerCaixa {

	@Autowired
	ServiceCaixa serviceCaixa;

	@Autowired
	ErrorsMsg error;

	@Autowired
	DataPresenter data;

	private static final Logger logger = LoggerFactory.getLogger(ControllerCaixa.class);

	@GetMapping(value = "/check/{user}/{password}")
	public ResponseEntity<?> checkUser(HttpServletRequest request, @PathVariable String user,
			@PathVariable String password) {

		logger.info("Logando usuario");
		if (serviceCaixa.checkUsers(user, password)) {

			logger.info("Login efetuado com sucesso");
			data.setData("Login efetuado com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(data);

		} else {

			String msg = "USUARIO/SENHA invalido!";
			logger.error("USUARIO/SENHA invalido!");
			return error.errorCustom(msg);
		}
	}

	@GetMapping(value = "/checkRetirement/{user}")
	public ResponseEntity<?> checkRetirement(HttpServletRequest request, @PathVariable String user) {

		logger.info("Checando valor e meses à receber do benefício");
		if (!(serviceCaixa.checkRetirementMoth(user).equals("0")) && (serviceCaixa.checkRetirementMoth(user) != null)) {

			logger.info("Valor retornado com sucesso");

			data.setData(String.format("Valor recebido por cada mes: R$%s  - Meses pra receber: %s",
					serviceCaixa.checkRetirementMoth(user), serviceCaixa.checkMoth(user)));
			return ResponseEntity.status(HttpStatus.OK).body(data);
		} else {

			logger.error("Meses de aposentadoria/Saldo menor ou igual a 0");
			String msg = "Meses de aposentadoria/Saldo menor ou igual a 0!";
			return error.errorCustom(msg);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(HttpServletRequest request, @RequestBody ObjProducer objProd) {

		logger.info("Cadastrando novo usuário");
		if (serviceCaixa.registerUser(objProd)) {

			logger.info("Dados cadastrados com sucesso");
			data.setData("Registros salvos com sucesso!");
			return ResponseEntity.status(HttpStatus.OK).body(data);
		} else {
			logger.error("Não foi possível salvar os dados, favor validar campos obrigatórios");
			String msg = "Não foi possível salvar os dados, favor validar campos obrigatórios";
			return error.errorCustom(msg);
		}

	}

}
