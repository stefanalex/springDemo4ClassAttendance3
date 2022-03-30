package com.warpit.demo.uc2.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.warpit.demo.scheduler.Scheduler;
import com.warpit.demo.scheduler.SchedulerDAO;
import com.warpit.demo.scheduler.SchedulerRepository;
import com.warpit.demo.uc1.domain.Activity;
import com.warpit.demo.uc1.domain.ActivityDTO;
import com.warpit.demo.uc1.repo.ActivityRepository;
import com.warpit.demo.uc2.domain.Confirmation;
import com.warpit.demo.uc2.domain.Student;
import com.warpit.demo.uc2.domain.StudentDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CheckInController {

	@Autowired
	ActivityRepository activityRepository;

	@PostMapping("/checkIn/{activityName}")
	public Confirmation getActivityForQRCodeKey(@PathVariable String activityName ,@RequestBody StudentDTO student) {
		
		log.info("We need to check in student for activity : "+activityName);
		log.info("We need to check in student for activity : "+student.toString());
		
		Activity activity = activityRepository.findByActivityName(activityName).orElseThrow(() -> new
                                                  NoSuchElementException("Activity not registerd" + activityName));
		
		
	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime activityStartTime = activity.getActivityStratDate(); 
		LocalDateTime studentStartTime = student.getCheckInTime();
		
		if(studentStartTime.isAfter(activityStartTime))
			             throw new NoSuchElementException("You cant check in after activity started");
		
		//other checks ...
		 

		return Confirmation.builder()
				           .activityName(activityName)
				           .studentName(student.getFirstName())
				           .displayMessage("Student : "+student.getFirstName()+" is chekIn for "+activityName).build();

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
