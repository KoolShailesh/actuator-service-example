package com.example.cacheregister;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.actuate.metrics.cache.CacheMeterBinderProvider;
import org.springframework.stereotype.Component;

/**
 * 
 * @author: https://medium.com/@iliamsharipov_56660
 * @see https://medium.com/@iliamsharipov_56660/spring-boot-actuator-for-concurrentmapcache-2c7f0d290934
 *
 */
@Component
public class ConcurrentMapCacheBinderProvider implements CacheMeterBinderProvider<ConcurrentMapCacheMetricsWrapper> {

	@Override
	public MeterBinder getMeterBinder(ConcurrentMapCacheMetricsWrapper cache, Iterable<Tag> tags) {
		return new ConcurrentMapCacheMeterBinder(cache, tags);
	}
}