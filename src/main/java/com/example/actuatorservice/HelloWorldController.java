package com.example.actuatorservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.AvailableTag;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.ListNamesResponse;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.MetricResponse;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private MetricsEndpoint metricsEndpoint;
	
	
//	@Autowired
//	private DataSource dataosurce;

	@GetMapping("/hello-world")
	@ResponseBody
	public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
		
//		try {
//			System.out.println(dataosurce.getConnection());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/printmetricsmaxmem")
	@ResponseBody
	public MetricResponse printJavaMaxMemMetrics() {

		ListNamesResponse listNames = metricsEndpoint.listNames();

		listNames.getNames().stream().forEach(System.out::println);

		MetricResponse metric = metricsEndpoint.metric("jvm.memory.max", new ArrayList<>());
		System.out.println("metric (jvm.memory.max) ->" + metric);

		List<AvailableTag> availableTags = metric.getAvailableTags();

		availableTags.forEach(tag -> System.out.println(tag.getTag() + " : " + tag.getValues()));

		List<Sample> measurements = metric.getMeasurements();
		measurements.forEach(sample -> System.out.println(sample.getStatistic() + " : " + sample.getValue()));
		
		return metric;

	}

}
