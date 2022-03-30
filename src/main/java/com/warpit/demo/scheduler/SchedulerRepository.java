package com.warpit.demo.scheduler;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.warpit.demo.uc1.domain.Activity;



public interface SchedulerRepository extends MongoRepository<Scheduler, String> {
	
	//db.getCollection('scheduler').find({"scheduledActivities.activityQRCodeKey":"QAZWSX"})
	
	//Optional<Activity> findByScheduledActivitiesActivityQRCodeKey(String activityQRCodeKey);
	
	@Query(value="{'scheduledActivities.activityQRCodeKey' : ?0}",
		  fields= "{'activityName':1,'activityStratDate':1}")
	Optional<Scheduler> findActivitiesByQRCodeKey(String activityQRCodeKey);

}

