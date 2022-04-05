package com.warpit.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.warpit.demo.scheduler.Scheduler;
import com.warpit.demo.scheduler.SchedulerRepository;
import com.warpit.demo.uc1.domain.Activity;
import com.warpit.demo.uc1.repo.ActivityRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class SpringDemoTestApplication  {

	
	@Autowired
	SchedulerRepository schedulerRepository;

	@Autowired
	ActivityRepository activityRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoTestApplication.class, args);
	}

	

}
