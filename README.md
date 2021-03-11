[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
<img alt="Kotlin" src="https://img.shields.io/badge/kotlin-%230095D5.svg?&style=for-the-badge&logo=kotlin&logoColor=white"/>
<img alt="Spring" src="https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>
	<img alt="Docker" src="https://img.shields.io/badge/docker%20-%230db7ed.svg?&style=for-the-badge&logo=docker&logoColor=white"/>

# Kotlin Spring Start Project
This project is a template template for new microservices that uses Kotlin.


- [Kotlin](https://kotlinlang.org/)
- [Gradle - Kotlin DSL](https://gradle.org/)
- [Docker](https://www.docker.com/)
- [Spring Boot](https://spring.io/)
- [Spring JPA](https://spring.io/)
- [Postgresql](https://www.postgresql.org/)
- [Kotest](https://kotest.io/)
- [MockK](https://mockk.io/)
- [TestContainers](https://www.testcontainers.org/)
- [Avro Plug in](https://github.com/davidmc24/gradle-avro-plugin)
- [Docker compose plug in](https://github.com/avast/gradle-docker-compose-plugin)

To build this project:

```sh
./gradlew build
```

To run:
```sh
./gradlew bootRun
```
bootRun initializes the project with an embedded postgresql container that was created with docker-compose file in project.

Check if the project is running, access the path (GET method)

```sh
localhost:8080/helloworld
```

This will print *"Hello, user!"*
