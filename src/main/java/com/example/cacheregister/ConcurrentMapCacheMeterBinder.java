package com.example.cacheregister;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.cache.CacheMeterBinder;

/**
 * 
 * @author: https://medium.com/@iliamsharipov_56660
 * @see https://medium.com/@iliamsharipov_56660/spring-boot-actuator-for-concurrentmapcache-2c7f0d290934
 *
 */
public class ConcurrentMapCacheMeterBinder extends CacheMeterBinder {

	private final ConcurrentMapCacheMetricsWrapper cache;

	public ConcurrentMapCacheMeterBinder(ConcurrentMapCacheMetricsWrapper cache, Iterable<Tag> tags) {
		super(cache, cache.getName(), tags);
		this.cache = cache;
	}

	@Override
	protected Long size() {
		return (long) cache.getNativeCache().size();
	}

	@Override
	protected long hitCount() {
		return cache.getHitCount();
	}

	@Override
	protected Long missCount() {
		return cache.getMissCount();
	}

	@Override
	protected Long evictionCount() {
		return cache.getEvictCount();
	}

	@Override
	protected long putCount() {
		return cache.getPutCount();
	}

	@Override
	protected void bindImplementationSpecificMetrics(MeterRegistry meterRegistry) {

	}
}