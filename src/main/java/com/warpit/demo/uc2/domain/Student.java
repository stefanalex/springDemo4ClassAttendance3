package com.warpit.demo.uc2.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Student {

	@Id
    private String id;
	
	private String firstName;
	private String lastName;
	
	private String registrationId;
		
}	
	

	
	
	
	
