"# SiiSii_Exercise" 

Application SiiSii_Exercise
This application simulate booking platform for conference lectures.
Requirements:
- Java 11
- Spring boot
- Postman
- Web browser
- H2 database

  How to run application:
1.	Load application with dependencies written in pom.xml file
2.	Build and run application
3.	Open web browser and go to:
      http://localhost:8080/h2-console/
4.	Connect with database with login: “sa”, password: “password”
5.	Copy/Paste Database inserts from Lecture.txt (if you want, you can change e.g. Title of Lecture) and Run. Now you have implemented Lectures entities to database.
      Note: in long perspective and different database this will not need to be repeated. You need only once to insert those data to database.
6.	Run Postman
      a)	If you want to create Participant you should create:
      I.	POST  http://localhost:8080/api/participant
      II.	and make JSON e.g. like this:

{
"participantName" : "Joe",
"email" : "joe@gmail.com"
}

III.	Then press the SEND button.

NOTE: Name is being validate by REGEX, so it needs to be in range of 2-30 characters
NOTE: email is being validate by REGEX, so it needs to be “likely” email.
NOTE: it is impossible to create Participant with already existing in database email or login.

b)	If you want to change Participants email you should:
I.	PUT  http://localhost:8080/api/participant/login/{participantLogin}/update_email
NOTE: {participantLogin} bracket should contain existing login/name of the Participant e.g. “Joe” from a) instruction
II.	and make JSON e.g. like this:

{
"email" : "joedoe@gmail.com"
}

III.	Than press the SEND button.
NOTE: it is impossible to change email for already existing in database email.
c)	If you want to view list of all already existing Participants in database, you should:
I.	GET http://localhost:8080/api/all_participants
II.	Then press the SEND button

d)	If you want to view all existing in database Lectures you should:
I.	GET http://localhost:8080/api/lectures
II.	Then press the SEND button.

e)	If you want to book place on any from Database Lecture for the Participant you should:
I. PUT  http://localhost:8080/api/{participantName}/{participantEmail}/lecture/{lectureID}

NOTE: {participantLogin}, {participantEmail} brackets should contain existing login/name and email of the Participant e.g. “Joe” from a) instruction. {lectureID} should contain existing lecture ID from database.
II. Then press the SEND button.
NOTE: After pressing SEND button will generate in TARGET location of project txt document mocking email that would be sent to Participant after booking Lecture.
f)	If you want to view on what Lectures participant is assigned to you should:

I.	GET http://localhost:8080/api/participant/login/{participantName}

NOTE: {participantLogin} bracket should contain existing login/name of the Participant e.g. “Joe” from a) instruction
II.	Then press the SEND button.

g)	You can do the same as point f) but only with ParticipantID
I.	GET http://localhost:8080/api/participant/login/{participantID}

NOTE: {participantID} bracket should contain existing ID of the Participant e.g. “Joe” from a) instruction. You can check the ID of the Participant only in H2 database.
II.	Then press the SEND button.


