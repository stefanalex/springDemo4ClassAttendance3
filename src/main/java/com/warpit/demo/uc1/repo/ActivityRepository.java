package com.warpit.demo.uc1.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.warpit.demo.scheduler.Scheduler;
import com.warpit.demo.uc1.domain.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String>{
	
	Optional<Activity> findByActivityName(String activityName);

}
