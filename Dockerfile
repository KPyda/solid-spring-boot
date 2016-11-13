FROM java:8
VOLUME /tmp
ADD target/solid.jar solid.jar
RUN sh -c 'touch /solid.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/solid.jar"]