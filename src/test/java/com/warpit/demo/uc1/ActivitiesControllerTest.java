package com.warpit.demo.uc1;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import com.warpit.demo.SpringDemoApplication;
import com.warpit.demo.security.AppUserDetailService;
import com.warpit.demo.security.JwtUtil;
import com.warpit.demo.uc1.domain.ActivityDTO;
import com.warpit.demo.uc1.web.ActivitiesController;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SpringDemoApplication.class)
@Slf4j
public class ActivitiesControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private ActivitiesController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AppUserDetailService appUserDetailService;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(appUserDetailService).isNotNull();
	}

	@Test
	public void getActivityForQRCodeKeyNotAuthorized() throws Exception {

		ResponseEntity<String> response = restTemplate.getForEntity("/activities/QAZWSX1234", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	public void getActivityForQRCodeKeyAuthorized() throws Exception {

		UserDetails userDetails = appUserDetailService.loadUserByUsername("student");
		ActivityDTO historyDTO = ActivityDTO.builder().activityName("History").activityBreak(10).activityDuration(50)
				.activityQRCodeURL("http://localhost:8080/checkIn/History")
				.activityStratDate(LocalDateTime.parse("2022-03-30 14:00:00", formatter)).build();
		final String jwt = new JwtUtil().generateToken(userDetails);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + jwt);
		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange("/activities/QAZWSX1234", HttpMethod.GET, entity,
				String.class);
		// log.info(response.getBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();

		assertThat(response.getBody().equals(historyDTO.toString()));

	}
}
