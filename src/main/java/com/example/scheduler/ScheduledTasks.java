package com.example.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private int i =0;

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		System.out.println(" reportCurrentTime The time is now " + dateFormat.format(new Date()));
	}

	
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime1() throws Exception{
		System.out.println("reportCurrentTime1 The time is now  [" + this.i++ + "]" + dateFormat.format(new Date()));
		if(i > 20) {
		}
	}
	
}
