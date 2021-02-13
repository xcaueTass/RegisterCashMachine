package com.project.caixaeletronico.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

	@Value("${timeout.connect}")
	private int timeoutConnect;

	@Value("${timeout.read}")
	private int timeoutRead;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.hikari.pool-name}")
	private String poolName;

	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minumunIdle;

	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maximunPoolSize;

	@Value("${spring.datasource.hikari.connection-timeout}")
	private Long connectionTimeout;

	@Value("${spring.datasource.hikari.idle-timeout}")
	private Long idleTimeout;

	@Value("${spring.datasource.hikari.max-lifetime}")
	private Long maxLifetime;

	public int getTimeoutConnect() {
		return timeoutConnect;
	}

	public int getTimeoutRead() {
		return timeoutRead;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPoolName() {
		return poolName;
	}

	public int getMinumunIdle() {
		return minumunIdle;
	}

	public int getMaximunPoolSize() {
		return maximunPoolSize;
	}

	public Long getConnectionTimeout() {
		return connectionTimeout;
	}

	public Long getIdleTimeout() {
		return idleTimeout;
	}

	public Long getMaxLifetime() {
		return maxLifetime;
	}

}
