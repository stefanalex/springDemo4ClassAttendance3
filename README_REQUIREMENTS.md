# springDemo4ClassAttendance2
demo app

##Task
You are designing a system which allows students (system users) to register class attendance
using QR codes. Assume that QR codes are posted on walls/desks (e.g. unique per classroom).
Class schedule is computerized and information about activities in each respective classroom is
known to the system backend.

##Goals
Your goal is to design a backend API to do the following:
1. Retrieve current activities for a given QR code (effectively, classroom ID)
2. 'Check in' for a selected activity
Current activities are determined as follows: a class that is either about to start or just started
(it's up to the implementor to reason & decide about time intervals to use).

Schedule implementation is out of scope for this assignment (e.g. scheduled activities can be
mocked, but completed assignment should clearly demonstrate their usage).
QR code scanning is also out of scope: you may think how to better protect the QR code
contents, but you don't need to implement the scanning itself.

##Implementation expectations
Implementation should basically contain the following REST API endpoints:
1.Endpoint to retrieve current activities;
2.Endpoint to perform/commit the check-in.

You are free to add APIs if necessary (you'll be expected to explain your decision).

#Things to consider:

1.What you are implementing for the check-in is effectively a 2-phase transaction (retrieve
the eligible activity / perform the check-in). 
How would you ensure transactional integrity in this case (e.g. make sure that check-in is performed by the same person who made
the retrieval, and the request is not forged)? 
Which technology standard would prevent tampering with the data?

2.Think how you would prevent QR code abuse (for ex. taking a picture of the QR code
and using it outside the classroom)?

#Language/framework to use
Any language/REST API framework. Live working demo is a plus, but not mandatory.
#Test coverage
You are expected to provide full unit test coverage for your implementation
