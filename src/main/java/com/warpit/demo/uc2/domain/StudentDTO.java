package com.warpit.demo.uc2.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class StudentDTO {

	private String firstName;
	private String lastName;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkInTime;
	
	
}	
	

	
	
	
	
