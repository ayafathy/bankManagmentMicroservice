# Microservices bank application for manage Customers and Accounts


# rabbit Mq used to send notification to the customer 
1-To run RabbitMQ on Docker Desktop for Windows, you can use the following command in the command prompt or PowerShell. Make sure you have Docker Desktop installed and running on your Windows machine.

docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

2-the RabbitMQ management console at http://localhost:15672/:
Username: guest
Password: guest


# h2 is used as the data base for url for each service 
  http://localhost:8086/h2-console/ >> database for Account service 
  http://localhost:8085/h2-console/ >> database for customer  service 
#swagger url  
http://localhost:8081/swagger-ui.html

 

"# bankManagment Microservice" 
