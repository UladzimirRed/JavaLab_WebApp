News Management.
Multi-modul web application using Spring Framework and Hibernate ORM technologies.

Technology stack:
- JDK version: 8.
- Application container: Spring IoC (Spring Framework version 5.x).
- Data access: JPA (Hibernate as JPA Provider). [in modul "repository-jpa"]
- Another way to data access: Spring JDBC Template. [in modul "repository-jdbc"]
- Database: PostgreSQL 10.+
- Database connection pool: HikariCP.
- Bean validation: Hibernate Validator.
- Build tool: Apache Maven 3.5+. Multi-module project.
- Web server: Apache Tomcat.
- Service layer covered with unit tests. 
- Repository layer tested using integration tests with an in-memory embedded database. 
- Testing: JUnit 4.+, Mockito.

Business Requirements:
- Backend for simple News Management application. Pay attention that this application without view layer (UI).
APIs demonstrated using Postman tool.

The system expose REST APIs to perform the following operations:
- CRUD operations for News.
If new tags and(or) Author are passed during creation or modification of a News, then they should be created in the database. 
- CRUD operations for Tag.
- CRUD operations for Author.
- Get news. All request parameters are optional and can be used in conjunction.
- Sort news tag or author.
- Search according SearchCriteria.
- Add tag/tags for news.
- Add author for news.
- Each news may have more than 1 tag.
