# Disaster-Management---A-Spring-Boot-Web-App
 
A Spring Boot-based web application developed for disaster management. It allows admins to manage crises, volunteers, donations, and inventory. Volunteers can log in, respond to crises, and handle relief tasks. Anonymous users can report crises and donate funds.

## Features

- **User Types**:
  - **Admin**: Manages and assigns tasks to volunteers, approves crises, and manages donations and inventory.
  - **Volunteer**: Logs in, registers, and responds to assigned tasks. Can purchase relief supplies and update personal information.
  - **Anonymous**: Can report crises and donate funds.
  
- **Donation & Fund Management**:
  - Anonymous users can donate.
  - View daily donation and expense charts.

- **Crisis Management**:
  - Report crises with location, severity, description, and required assistance.
  
- **Inventory Management**:
  - Volunteers can manage relief supplies like food, water, and medical items.

- **Admin Reports**:
  - Admins can export daily donation, expense, and inventory reports (CSV/Excel).
  - (This feature is under development)

## Technologies Used

- **Frontend**: HTML, CSS, Bootstrap, JavaScript and JQuery etc.
- **Backend**: Spring Boot (Java)
- **Database**: MySQL (relational database)

## Installation & Setup

### Prerequisites

- **JDK 11** or later
- **Maven**
- **MySQL** as relational database

### Steps to Set Up Locally

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Zahid-Hasan-Mozumder/Disaster-Management---A-Spring-Boot-Web-App.git
   cd Disaster-Management---A-Spring-Boot-Web-App
   cd DisasterManagement
   ```

2. **Build and Run the Application**:

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

3. **Access the Application**:

   - Open your browser and go to http://localhost:8080.

4. **Login**

   - Initiatial login for admin is
   ```
   Email: jahidhasanmozumder@gmail.com
   Password: test123
   ```
   - Initial login for volunteer is
   ```
   Email: Any Registered Volunteer's Email
   Password: test123
   ```

5. **Extras**

   - The MySQL files are attached here for your manual use.
   - You can open your MySQL Workbench and
   - Run all the scripts (For get a working understanding of the web app with preloaded data) or
   - Run all the scripts except the 8th script (For get a initial start of the web app to populate it later with your own inserted data).
