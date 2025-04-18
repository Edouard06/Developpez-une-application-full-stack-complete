Project Setup Guide

1.Cloning the Repository
      
Clone the project repository:
git clone https://github.com/Edouard06/Developpez-une-application-full-stack-complete

 2. Installing Dependencies
      
Frontend:
Navigate to the frontend directory and install dependencies:
cd front
npm install

Backend:
Navigate to the backend directory and install Maven dependencies:
cd ../back
mvn install

3.Database Setup
      
Ensure MySQL is installed on your machine. If not, install it via:
https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/
Update your MySQL credentials in 'back/src/main/resources/application.properties':
- spring.datasource.username
- spring.datasource.password
Launch MySQL and create the database:
- On Linux/Mac: mysql -u root -p
- On Windows: use the MySQL Command Line Client
- Inside MySQL:
  CREATE DATABASE mdd;
  USE mdd;
  Import schema: mysql -u root -p mdd < ressources/sql/script.sql
  
4. Running the Application
      
Backend:
In the back directory, start the server:
mvn spring-boot:run

Frontend:
In the front directory, start the Angular app:
ng serve
Visit: http://localhost:4200

 5. Features Overview
- Register and login users.
- Subscribe to existing themes.
- Create and view articles.
- Update profile details.
- Responsive UI for different screen sizes.
