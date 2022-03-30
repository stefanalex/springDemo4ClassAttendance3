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
public class Scheduler {

	@Id
    private String id;
	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime day;
	
	
	private List<Activity> scheduledActivities;
	
	
	


	
	
	
	

}
