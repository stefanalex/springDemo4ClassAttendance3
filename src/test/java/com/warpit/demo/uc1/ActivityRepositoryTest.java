package com.warpit.demo.uc1;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

import com.warpit.demo.uc1.domain.Activity;
import com.warpit.demo.uc1.repo.ActivityRepository;
import com.warpit.demo.SpringDemoTestApplication;


@DataMongoTest
@ContextConfiguration(classes=SpringDemoTestApplication.class)
class ActivityRepositoryTest {
	
	@Autowired
	private ActivityRepository activityRepository;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(activityRepository).isNotNull();
	}

	@Test
	void createActivity() {

		String activityQRCodeURL = "http://localhost:8080/checkIn/";
		Activity math = Activity.builder().activityBreak(10).activityDuration(50).activityName("Math")
				.activityStratDate(LocalDateTime.parse("2022-03-30 16:00:00", formatter))
				.activityQRCodeURL(activityQRCodeURL).activityQRCodeKey("QAZWSX7890")
				.build();

		activityRepository.save(math);

		assertThat(activityRepository.findById(math.getId())).isNotEmpty();
	}

	
	@Test
	void findByActivityName() {

		String activityQRCodeURL = "http://localhost:8080/checkIn/";
		Activity math = Activity.builder().activityBreak(10).activityDuration(50).activityName("Math")
				.activityStratDate(LocalDateTime.parse("2022-03-30 16:00:00", formatter))
				.activityQRCodeURL(activityQRCodeURL).activityQRCodeKey("QAZWSX7890")
				.build();

		activityRepository.save(math);

		System.out.println(">>>>><<<<<"+activityRepository.findByActivityName("Math"));
		assertThat(activityRepository.findByActivityName("Math")).isNotEmpty();
	}
	
}
