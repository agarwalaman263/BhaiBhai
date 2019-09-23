java -jar files\simian\bin\simian-2.5.10.jar **\*.java
call mvn clean
call mvn compile
call mvn test-compile
call mvn install
start ./target/site/jacoco/index.html
call mvn spring-boot:run
pause


