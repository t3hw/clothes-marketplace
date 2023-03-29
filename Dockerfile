FROM maven:3.8.5 AS build
COPY src /home/app/clothes-marketplace/src
COPY swagger_impl /home/app/swagger_impl
RUN mvn install:install-file -DpomFile=/home/app/swagger_impl/server_swaggers.xml -Dfile=/home/app/swagger_impl/clothes_marketplace_server_swaggers-1.0.0.jar -DgroupId=com.ravid.clothes_marketplace -DartifactId=clothes_marketplace_server_swaggers -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=false
COPY pom.xml /home/app/clothes-marketplace/pom.xml
RUN  --mount=type=cache,target=/root/.m2 mvn -Dmaven.test.skip -f /home/app/clothes-marketplace/pom.xml clean package


#
# Package stage
#
FROM openjdk:17-jdk-oraclelinux8
COPY --from=build /home/app/clothes-marketplace/target/*.jar /lib/clothes-marketplace.jar
COPY properties /lib/properties
EXPOSE 2000
ENTRYPOINT ["java","-jar","/lib/clothes-marketplace.jar"]
