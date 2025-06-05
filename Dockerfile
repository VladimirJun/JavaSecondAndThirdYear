FROM openjdk:17-jdk-slim

  # Устанавливаем рабочую директорию
WORKDIR /app

  # Копируем собранный JAR файл в контейнер
COPY target/demo1-0.0.1-SNAPSHOT.jar app.jar

  # Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]