package com.warpit.demo.uc1;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.warpit.demo.scheduler.Scheduler;
import com.warpit.demo.uc1.domain.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String>{

}
