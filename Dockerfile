FROM java:8-jdk-alpine
VOLUME /tmp
COPY ./target/*.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8080
RUN sh -c 'touch app.jar'
ENTRYPOINT ["java","-jar","app.jar"]