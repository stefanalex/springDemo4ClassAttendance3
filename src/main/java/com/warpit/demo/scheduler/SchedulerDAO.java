package com.warpit.demo.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.warpit.demo.uc1.domain.Activity;
import com.warpit.demo.uc1.domain.ActivityDTO;

@Repository
public class SchedulerDAO {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Scheduler> findAll(){
		return mongoTemplate.findAll(Scheduler.class);
	}
	
	
   public List<ActivityDTO> findActivitiesByQRCodeKey(String qrCodeKey){
	   List<ActivityDTO> activityFound = new ArrayList<ActivityDTO>();
	   List<Scheduler> schedulerList = findAll();
	   
	   Predicate<Activity> qrCodeFound = activity -> activity.getActivityQRCodeKey().contentEquals(qrCodeKey);
	   
	   schedulerList.stream().forEach(schedule->{
		   Activity activity =schedule.getScheduledActivities().stream().filter(qrCodeFound).findFirst().orElse(null)   ;
		   activityFound.add(ActivityDTO.builder().activityQRCodeURL(activity.getActivityQRCodeURL())
				                               .activityStratDate(activity.getActivityStratDate())
				                               .activityDuration(activity.getActivityDuration())
				                               .activityBreak(activity.getActivityBreak())
				                               .activityName(activity.getActivityName()).build()
				   );   
	   });
	   
	  	   
       return activityFound;
       
   }   
}
