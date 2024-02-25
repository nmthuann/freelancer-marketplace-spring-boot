FROM openjdk:23-ea-11-jdk-oraclelinux8

WORKDIR /nmthuann/freelancer-marketplace-spring-boot

COPY target/*.jar /nmthuann/freelancer-marketplace-spring-boot/app.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]