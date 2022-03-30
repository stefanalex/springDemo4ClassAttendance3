package com.warpit.demo.scheduler;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;



public interface SchedulerRepository extends MongoRepository<Scheduler, String> {
	
	//db.getCollection('scheduler').find({"scheduledActivities.activityQRCodeKey":"QAZWSX"})
	
	//Optional<Activity> findByScheduledActivitiesActivityQRCodeKey(String activityQRCodeKey);
	
	//@Query(value="{'scheduledActivities.activityQRCodeKey' : ?0}",
	//	  fields= "{'activityName':1,'activityStratDate':1}")
	//Optional<Activity> findActivitiesByQRCodeKey(String activityQRCodeKey);

}

//"activityName": null,
//"activityDuration": null,
//"activityBreak": null,
//"activityStratDate": null,
//"activityQRCodeURL": null,
//"activityQRCodeKey": null