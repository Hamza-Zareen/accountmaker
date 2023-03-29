FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} accountmaker.jar
ENTRYPOINT ["java","-jar","/accountmaker.jar"]