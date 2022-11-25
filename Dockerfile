FROM openjdk:17-jdk-oraclelinux8
FROM maven:3.8.5 AS build
COPY src /home/app/clothes-marketplace/src
COPY swagger_impl /home/app/swagger_impl
RUN mvn install:install-file -DpomFile=/home/app/swagger_impl/swagger_server.xml -Dfile=/home/app/swagger_impl/clothes_marketplace_server_swaggers-1.0.0-sources.jar -DgroupId=com.ravid.clothes_marketplace -DartifactId=clothes_marketplace_server_swaggers -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=false
COPY pom.xml /home/app/clothes-marketplace/pom.xml
RUN mvn -Dmaven.test.skip -f /home/app/clothes-marketplace/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-jdk-oraclelinux8
COPY --from=build /home/app/clothes-marketplace/target/*.jar /lib/clothes-marketplace.jar
COPY certs /lib/certs
COPY properties /lib/properties
EXPOSE 2000
ENTRYPOINT ["java","-jar","/lib/clothes-marketplace.jar"]

# Local env variables
# ENV DbUrl "jdbc:mysql://localhost:3306/"
# ENV DbName "db"
# ENV DBusername "admin"
# ENV DBpassword "admin123"
# ENV LOGGER_APPENDER "Text"
# ENV SPRING_CONFIG_NAME "application,additionalProperties"
# ENV SPRING_CONFIG_LOCATION "/lib/properties/"