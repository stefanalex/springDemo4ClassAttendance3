package com.warpit.demo.uc1.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.warpit.demo.scheduler.Scheduler;
import com.warpit.demo.scheduler.SchedulerDAO;
import com.warpit.demo.scheduler.SchedulerRepository;
import com.warpit.demo.uc1.domain.Activity;
import com.warpit.demo.uc1.domain.ActivityDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ActivitiesController {

	@Autowired
	SchedulerRepository schedulerRepository;
	
	@Autowired
	SchedulerDAO schedulerDAO;

	@RequestMapping("/activities")
	public List<Scheduler> findAllSchedules() {
		//return schedulerRepository.findAll();
		return schedulerDAO.findAll();
	}

	@GetMapping("/activities/{qrCodeKey}")
	public List<ActivityDTO> getActivityForQRCodeKey(@PathVariable String qrCodeKey) {

		List<ActivityDTO> activityList = schedulerDAO.findActivitiesByQRCodeKey(qrCodeKey);
		if(activityList.size()==0) throw new NoSuchElementException("QRCode not registered for any activity" + qrCodeKey);
		
	//	log.info(">>>><<<<<<<<<<<<"+schedulerDAO.findActivitiesByQRCodeKey(qrCodeKey));
		return activityList;

	}

	/**
	 * Exception handler if NoSuchElementException is thrown in this Controller
	 *
	 * @param ex exception
	 * @return Error message String.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();

	}

}
