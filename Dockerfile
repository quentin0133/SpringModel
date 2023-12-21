FROM rsunix/yourkit-openjdk17

ADD target/awesome-project.jar awesome-project.jar
ENTRYPOINT ["java", "-jar", "awesome-project.jar"]
EXPOSE 8080