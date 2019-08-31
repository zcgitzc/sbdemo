FROM java:8
VOLUME /tmp
ADD boot-start-0.0.1-SNAPSHOT sbdemo.jar
RUN sh -c 'touch /sbdemo.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]