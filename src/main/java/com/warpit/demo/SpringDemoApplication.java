package com.warpit.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.warpit.demo.scheduler.Scheduler;
import com.warpit.demo.scheduler.SchedulerRepository;
import com.warpit.demo.uc1.ActivityRepository;
import com.warpit.demo.uc1.domain.Activity;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class SpringDemoApplication implements CommandLineRunner {

	
	@Autowired
	SchedulerRepository schedulerRepository;

	@Autowired
	ActivityRepository activityRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("Schedule intialization started ..");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String activityQRCodeURL = "http://localhost:8080/activities/checkIn?activityName=";
				
		Activity history = Activity.builder().activityBreak(10).activityDuration(50).activityName("History")
				.activityStratDate(LocalDateTime.parse("2022-03-30 14:00:00", formatter))
				.activityQRCodeURL(activityQRCodeURL).activityQRCodeKey("QAZWSX1234")
				.build();
		
		activityRepository.save(history);
		
		
		Activity sport = Activity.builder().activityBreak(10).activityDuration(50).activityName("Sport")
				.activityStratDate(LocalDateTime.parse("2022-03-30 15:00:00", formatter))
				.activityQRCodeURL(activityQRCodeURL).activityQRCodeKey("QAZWSX4567")
				.build();
		
		activityRepository.save(sport);
		
		Activity math = Activity.builder().activityBreak(10).activityDuration(50).activityName("Math")
				.activityStratDate(LocalDateTime.parse("2022-03-30 16:00:00", formatter))
				.activityQRCodeURL(activityQRCodeURL).activityQRCodeKey("QAZWSX7890")
				.build();
		
		activityRepository.save(math);

		Scheduler scheduler = Scheduler.builder()
				                       .day(LocalDateTime.parse("2022-03-30 08:00:00", formatter))
				                       .scheduledActivities(List.of(history,sport,math)).build();
		schedulerRepository.save(scheduler);

		log.info("Schedule intialization end");
	}

}
