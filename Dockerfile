# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim AS build

# Устанавливаем рабочую директорию в контейнере
WORKDIR /workspace/app

# Копируем исходный код проекта
COPY src src

# Копируем gradle props и wrapper в контейнер
COPY gradlew gradlew
COPY gradle gradle
COPY settings.gradle settings.gradle
COPY build.gradle build.gradle
# Загружаем все зависимости Gradle
RUN ./gradlew build --no-daemon || return 0

# Собираем проект
RUN ./gradlew bootJar --no-daemon

# Используем многостадийную сборку для создания минимизированного образа
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем JAR-файл из предыдущей стадии
COPY --from=build /workspace/app/build/libs/*.jar /app/

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app/licensing-service-0.0.1-SNAPSHOT.jar"]