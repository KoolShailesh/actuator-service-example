package com.example.cacheregister;

import java.util.stream.Collectors;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author: https://medium.com/@iliamsharipov_56660
 * @see https://medium.com/@iliamsharipov_56660/spring-boot-actuator-for-concurrentmapcache-2c7f0d290934
 *
 */
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {
	private final SpringProperties springProperties;

	public CacheConfig(SpringProperties springProperties) {
		this.springProperties = springProperties;
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(springProperties.getCacheNames().stream().map(ConcurrentMapCacheMetricsWrapper::new)
				.collect(Collectors.toList()));
		return cacheManager;
	}
}