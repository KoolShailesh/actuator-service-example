package com.example.cacheregister;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author: https://medium.com/@iliamsharipov_56660
 * @see https://medium.com/@iliamsharipov_56660/spring-boot-actuator-for-concurrentmapcache-2c7f0d290934
 *
 */
@ConfigurationProperties(prefix = "spring.cache")
public class SpringProperties {
	private List<String> cacheNames;

	public List<String> getCacheNames() {
		return cacheNames;
	}

	public void setCacheNames(List<String> cacheNames) {
		this.cacheNames = cacheNames;
	}
}