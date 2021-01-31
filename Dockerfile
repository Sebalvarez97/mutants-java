#################################
FROM maven:3.6.3-jdk-8-slim as builder
RUN mkdir /app
WORKDIR /app

# build
COPY .git/ ./.git
COPY pom.xml ./
COPY src/ src/
RUN mvn -U clean install

#################
###  STAGE 2  ###
###  RUN      ###
#################

FROM openjdk:8-jdk-alpine
ENV SERVER_PORT=8080

#Create a group and user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080
COPY --from=builder /app/target/*.jar /
ENTRYPOINT ["sh","-c","java -jar /*.jar --server.port=8080"]