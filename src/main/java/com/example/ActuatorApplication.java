package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.AvailableTag;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.ListNamesResponse;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.MetricResponse;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.Sample;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.cacheregister.SpringProperties;

@EnableConfigurationProperties({SpringProperties.class})
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ActuatorApplication {

	@Autowired
	private MetricsEndpoint metricsEndpoint;

	public static void main(String[] args) {
		SpringApplication.run(ActuatorApplication.class, args);
	}

	@PostConstruct
	public void inokeMetrics() {

		ListNamesResponse listNames = metricsEndpoint.listNames();

		listNames.getNames().stream().forEach(System.out::println);// NOSONAR

		MetricResponse metric = metricsEndpoint.metric("jvm.memory.max", new ArrayList<>());
		System.out.println("metric (jvm.memory.max) ->" + metric);

		List<AvailableTag> availableTags = metric.getAvailableTags();

		availableTags.forEach(tag -> System.out.println(tag.getTag() + " : " + tag.getValues()));// NOSONAR

		List<Sample> measurements = metric.getMeasurements();
		measurements.forEach(sample -> System.out.println(sample.getStatistic() + " : " + sample.getValue()));

	}

}
