FROM  eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .
RUN chmod +x gradlew
RUN ./gradlew build -x test
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["sh" ,"-c" "java -jar   app.jar --server.port=${PORT}"]