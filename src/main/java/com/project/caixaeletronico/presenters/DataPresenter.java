package com.project.caixaeletronico.presenters;

import org.springframework.stereotype.Component;

@Component
public class DataPresenter {

	private Object data;

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}
}
