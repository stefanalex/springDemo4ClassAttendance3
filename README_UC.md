# springDemo4ClassAttendance2

designing a system which allows students (system users) to register class attendance using QR codes.

##Use Cases

### UC1 - Retrieve activities

Name - Retrieve current activities for a given QR code (effectively, classroom ID)

Primary Actor - System user (students)

Preconditions - Class schedule and information about activities in each classroom are known 
              - Student is logged into the system

Postconditions - Action is performed only users that fit the specified criteria

Main Success Scenario
1. Student receive a QR code
2. Student use the phone to scan the QR code
3. The system find all of activities  that match the specified criteria .
4. The system will return all information need it : activity name ,if activity is started or not, classroom information

Extension -

Frequency of Occurence - Many times during the day


### UC2 - 'Check in

Name - 'Check in' for a selected activity

Primary Actor - System user (students)

Preconditions - Class schedule and information about activities in each classroom are known 
              - Student is logged into the system
              - Student selected a specified activity

Postconditions - Action is performed only users that fit the specified criteria

Main Success Scenario
1. Student select a activity from the list 
2. Student select the Submit button
3. The system will enroll the student for activity


Extension -

Frequency of Occurence - Many times during the day



