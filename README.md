TO SEE THE FULL DOCUMENTATION PLEASE FOLLOW THE LINK: https://drive.google.com/file/d/1VJSLkgLrNfYpKSDJkLjihaR1YTAEAceS/view?usp=drive_link

# Installation and launch

1. Install Java Development Kit (JDK) version 8 or higher.
2. Install PostgreSQL and create a database for the project.
3. Clone repository 

```sql
git clone <repository-url>
```

4. You will need IntelliJ IDEA(Ultimate version preferably) or Eclipse as IDE
5. Create a scheme in Postgres
6. Open src/main/sql/library_management_system.sql then open Postgres Console and copy paste the SQL code to get the tables created
7. Set up your **application properties file** 

```sql
spring.datasource.url=jdbc:postgresql://<database-url>:<port>/<database-name>
spring.datasource.username=<database-username>
spring.datasource.password=<database-password>
```

8. Deploy your project
