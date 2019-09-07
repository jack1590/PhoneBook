FROM java:8
VOLUME /tmp
ADD /target/phonebook.jar app.jar
EXPOSE 8080
ADD wait-for-it.sh /wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "docker-mysql:3306", "--timeout=45", "--", "java", "-jar", "app.jar"]