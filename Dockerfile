FROM maven:3.8.5 AS build

WORKDIR /home/app

ADD src src
ADD swagger_impl swagger_impl
RUN --mount=type=cache,target=/root/.m2 mvn install:install-file -DpomFile=swagger_impl/server_swaggers.xml -Dfile=swagger_impl/clothes_marketplace_server_swaggers-2.0.0.jar -DgroupId=com.ravid.clothes_marketplace -DartifactId=clothes_marketplace_server_swaggers -Dversion=2.0.0 -Dpackaging=jar -DgeneratePom=false
ADD pom.xml pom.xml
RUN --mount=type=cache,target=/root/.m2 mvn -Dmaven.test.skip -f pom.xml clean package


#
# Package stage
#
FROM openjdk:17-jdk-alpine
COPY --from=build /home/app/target/*.jar /lib/clothes-marketplace.jar
COPY properties /lib/properties
EXPOSE 2000
ENTRYPOINT ["java","-jar","/lib/clothes-marketplace.jar"]
