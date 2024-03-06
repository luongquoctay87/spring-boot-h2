# Sample Spring Boot Using H2

## 1. Pre-Requisites
- Install JDK
- Install Maven
- Install IntelliJ
- Install Docker

## 2. Technical Stacks
- Spring Boot 3.2.3
- H2 database
- JPA
- Swagger
- Devtool


## 3. Run application
- Using the Maven plugin
```bash
$ mvn spring-boot:run
```

- Running as a packaged application
```bash
$ mvn clean package
$ java -jar target/api-service.jar
```

- Using docker
```bash
$ mvn clean package
$ docker build -t api-service:latest .
$ docker run -it -p 8080:8080 api-service:latest api-service
```

## 4. Test API
[API Service Documents](http://localhost:8080/swagger-ui/index.html)
