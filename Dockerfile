FROM rsunix/yourkit-openjdk17

ADD 'target/awesome-project-1.0-SNAPSHOT.jar' 'awesome-project-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "awesome-project-1.0-SNAPSHOT.jar"]
EXPOSE 8080