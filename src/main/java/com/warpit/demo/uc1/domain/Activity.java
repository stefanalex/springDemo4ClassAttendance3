package com.warpit.demo.uc1.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
public class Activity {

	@Id
    private String id;
	
	
	private String activityName;
	
    @Min(0)
    @Max(50)
    private Integer activityDuration;  
    
    @Min(0)
    @Max(10)
    private Integer activityBreak;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activityStratDate;
    
    private String activityQRCodeURL;
    
    private String activityQRCodeKey;
    
  
	   
}
