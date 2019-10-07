Deployment Instruction
=============================

1. Export the env variables:

echo "export SENDGRID_API_KEY='YOUR_API_KEY'" >> mailsend.env

echo "export SENDGRID_URL='https://api.sendgrid.com/v3/mail/send'" >> mailsend.env

echo "export MAILGUN_API_KEY='YOUR_API_KEY'" >> mailsend.env

echo "export MAILGUN_URL='https://api.mailgun.net/v3/sandbox2741367f90b2469d80c91d72be15861b.mailgun.org/messages'" >> mailsend.env

echo "mailsend.env" >> .gitignore

source ./mailsend.env

2. download the required softwares:

   yum install java -y
   
   yum install git -y
   
   yum install maven -y

3. Clone the repository:

https://github.com/animuk/email-notification.git
	
4. Go to the Repository and Build the executable maven jar

	--> mvn package
	
5.Execute the Jar file as mentioned below:

	java -jar target/coding-challenge-1.0.3.jar
	
6.Now, REST Service is ready to serve the request

7. Open Postman and invoke the API: http://localhost:8080/sendmail with the body as:

   {
	"from" : "careers.anindya@gmail.com",
	"to": "a.mukherjee7@gmail.com",
	"subject": "Test Subject",
	"content": "I can see the magic content"
   }
   
   and voila.It works.
   
   Integrated with two email servers:
   1.Sendgrid as 1st email gateway
   2.Mailgun as failover gateway ( if the Sendgrid is down )
   
   For Mailgun, to use your email , you have to add your email as trusted recipient. then only it works.
   
   
	
