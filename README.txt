This sample application is the minimum set to get an spring boot application started
- web container
- spring data jpa
- spring mvc with thymeleaf
- and some other stuff


How to Build

* to compile-test, run "gradle test"
* to compile-test-build, run "gradle build"
* to compile-run, run "gradle run/bootRun"
* to dry run a task, run "gradle -m xxxTask"
* the test report will be generated at build/reports/tests/test/index.html


How to Test
* http://localhost:8080/reservations (MVC)
* http://localhost:8080/reservations?date=2019-01-01 (MVC)
* http://localhost:8080/api/reservations/2019-12-12 (MVC with REST)
* http://localhost:8080/api/reservations/2019-01-01 (MVC with REST)


Directory Structure
* web.application       - web front ui
* web.service           - web front rest service
* business.domain       - biz middle tier interface
* business.service      - biz middle tier service implementation
* data.entity           - data object
* data.repository       - data access object (powered by hibernate)
* data.webservice       - simple rest to expose data (just for testing)
* resources/static/*    - static frontend files
* resources/templates/* - thymeleaf templates
* resource/schema.sql   - DDL that automatically loaded by Spring
* resource/data.sql     - DML that automatically loaded by Spring


More about Data
* Spring automatically use schema.sql/data.sql to initialize the embedded database
* a few lines of "Executing SQL script from URL" are printed on the console
* to view the data in the embedded database, add two following lines to application.properties
* spring.jpa.hibernate.ddl-auto=none
* spring.h2.console.enabled=true
* start the spring boot application and visit the h2 console at http://localhost:8080/h2-console
* on the console, JDBC URL = jdbc:h2:mem:testdb, USER NAME = sa, PASSWORD = Empty
