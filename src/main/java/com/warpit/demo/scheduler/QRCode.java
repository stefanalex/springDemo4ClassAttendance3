package com.warpit.demo.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.warpit.demo.uc1.domain.Activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class QRCode {

	@Id
	private String id;

	// check in URL
	// http://localhost:8080/activities/mathematics/checkIn
	private String qrCodeURL;

	// unique code inside the system
	private String qrCodeKey;

	
	// session id - initial empty
	private String sessionID;
	
	// CSRF unique token - 
	private String CSRFToken;

}
