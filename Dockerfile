# =============================================
# Multi-stage build: 빌드 환경과 실행 환경 분리
# =============================================

# Stage 1: Build
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# Gradle Wrapper 및 설정 파일 먼저 복사 (캐시 최적화)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 실행 권한 부여
RUN chmod +x gradlew

# 의존성 먼저 다운로드 (소스 변경 시 캐시 재사용)
RUN ./gradlew dependencies --no-daemon || true

# 소스 코드 복사 후 빌드
COPY src src
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 빌드된 JAR 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080
ㅇ
# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
