FROM maven as build
WORKDIR /app
COPY . .
RUN mvn install

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/demo-1.jar /app
EXPOSE 9090
CMD ["java","-jar","demo-1.jar"]
