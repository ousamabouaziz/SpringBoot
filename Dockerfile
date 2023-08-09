FROM maven as build
WORKDIR /app
COPY . .
RUN mvn install -DskipTests

FROM openjdk:17

ENV DB_URL=
ENV DB_USERNAME=
ENV DB_PASSWORD=
ENV DB_VAR=
ENV DB_VARR=

WORKDIR /app
COPY --from=build /app/target/demo-1.jar /app
EXPOSE 9090
CMD ["java","-jar","demo-1.jar"]
