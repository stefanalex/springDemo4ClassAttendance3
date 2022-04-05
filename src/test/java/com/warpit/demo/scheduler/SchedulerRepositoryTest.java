package com.warpit.demo.scheduler;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.warpit.demo.uc1.domain.Activity;


@DataMongoTest
class SchedulerRepositoryTest {

	@Autowired
	private SchedulerRepository schedulerRepository;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(schedulerRepository).isNotNull();
	}

	@Test
	void createSchedule() {
		
		String activityQRCodeURL = "http://localhost:8080/checkIn/";

		Activity math = Activity.builder().activityBreak(10).activityDuration(50).activityName("Math")
				.activityStratDate(LocalDateTime.parse("2022-03-30 16:00:00", formatter))
				.activityQRCodeURL(activityQRCodeURL).activityQRCodeKey("QAZWSX7890")
				.build();

	
		Scheduler scheduler = Scheduler.builder()
				                       .day(LocalDateTime.parse("2022-03-30 08:00:00", formatter))
				                       .scheduledActivities(List.of(math)).build();
		schedulerRepository.save(scheduler);


		assertThat(schedulerRepository.findById(scheduler.getId())).isNotEmpty();
	}

	/**
	@Test
	void findActivitiesByQRCodeKey() {

		String activityQRCodeURL = "http://localhost:8080/activities/history/checkIn";

		LocalDateTime day = LocalDateTime.parse("2012-04-23 08:00:00", formatter);
		LocalDateTime activityStratDate = LocalDateTime.parse("2012-04-23 14:00:00", formatter);
		Activity activity = new Activity("History", 50, 10, activityStratDate, activityQRCodeURL, "QAZWSX");

		Scheduler scheduler = new Scheduler(day, List.of(activity));

		schedulerRepository.save(scheduler);

		Optional<Activity> activity2 = schedulerRepository.findActivitiesByQRCodeKey("QAZWSX");

		System.out.println(">>activity2>>" + activity2.get().toString());
	}**/
}
