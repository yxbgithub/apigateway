from java:8
COPY target/apigateway-0.0.1-SNAPSHOT.jar /opt/
workdir /opt/
EXPOSE 8082
ENTRYPOINT ["java","-jar","apigateway-0.0.1-SNAPSHOT.jar"]