package com.project.caixaeletronico.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.caixaeletronico.models.ObjProducer;

@SpringBootTest
class CaixaeletronicoApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private ControllerCaixa caixaControll;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ObjProducer obj;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(caixaControll).build();
	}

	@Test
	void testGETCheckUser() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/cashMachine/checkRetirement/caue"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGETLoginCashMachine() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/cashMachine/check/caue/teste"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testPOSTCreateUser() throws Exception {
		obj = new ObjProducer("testea", "senha", "00000000000", "myemail@gmail.com", 200.00, 1);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/cashMachine/register").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(obj)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
